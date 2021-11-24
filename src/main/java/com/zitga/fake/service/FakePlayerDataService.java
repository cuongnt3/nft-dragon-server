package com.zitga.fake.service;

import com.zitga.base.constant.LogicCode;
import com.zitga.bean.BeanContainer;
import com.zitga.bean.ReflectionScanner;
import com.zitga.bean.annotation.BeanComponent;
import com.zitga.bean.annotation.BeanField;
import com.zitga.bean.annotation.BeanMethod;
import com.zitga.enumeration.fake.FakePlayerDataType;
import com.zitga.fake.annotation.FakeDataController;
import com.zitga.fake.controller.BaseFakeDataController;
import com.zitga.player.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@BeanComponent
public class FakePlayerDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeanField
    private BeanContainer beanContainer;

    private Map<FakePlayerDataType, BaseFakeDataController> fakeDataControllerMap;

    @BeanMethod
    private void init() {
        logger.info("Initializing FakePlayerDataService ...");

        ReflectionScanner scanner = beanContainer.getScanner();
        Set<Class<?>> classes = scanner.scanAnnotatedType(FakeDataController.class);

        fakeDataControllerMap = new ConcurrentHashMap<>();
        for (Class<?> fakeDataClass : classes) {
            FakeDataController annotation = fakeDataClass.getAnnotation(FakeDataController.class);
            for (FakePlayerDataType type : annotation.value()) {
                if (fakeDataControllerMap.containsKey(type)) {
                    throw new RuntimeException(String.format("Duplicated type %s for %s", type, fakeDataClass));
                }

                BaseFakeDataController controller = (BaseFakeDataController) beanContainer.getComponent(fakeDataClass);
                if (controller == null) {
                    throw new RuntimeException(String.format("Controller is not found with type %s for %s", type, fakeDataClass));
                }

                fakeDataControllerMap.put(type, controller);
            }
        }
    }

    public int fakeData(Player player, FakePlayerDataType fakeType, String fakeData){
        int resultCode = LogicCode.SUCCESS;

        BaseFakeDataController controller = fakeDataControllerMap.get(fakeType);
        if (controller != null) {
            resultCode = controller.fakeData(player, fakeType, fakeData);
        }

        return resultCode;
    }
}