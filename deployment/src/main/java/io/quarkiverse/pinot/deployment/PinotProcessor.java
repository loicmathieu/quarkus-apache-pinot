package io.quarkiverse.pinot.deployment;

import java.util.List;

import io.quarkiverse.pinot.runtime.ConnectionProducer;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedClassBuildItem;

class PinotProcessor {

    private static final String FEATURE = "pinot";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    AdditionalBeanBuildItem producer() {
        return new AdditionalBeanBuildItem(ConnectionProducer.class);
    }

    @BuildStep
    List<RuntimeInitializedClassBuildItem> runtimeInitializedClass() {
        return List.of(
                new RuntimeInitializedClassBuildItem("org.asynchttpclient.RequestBuilderBase"),
                new RuntimeInitializedClassBuildItem("org.asynchttpclient.ntlm.NtlmEngine"),
                new RuntimeInitializedClassBuildItem("org.apache.zookeeper.Login"),
                new RuntimeInitializedClassBuildItem("org.apache.pinot.client.DynamicBrokerSelector"));
    }

    NativeImageResourceBuildItem nativeImageResource() {
        return new NativeImageResourceBuildItem("**/ahc-default.properties");
    }

}
