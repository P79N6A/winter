package com.panda.service.dubbo;

import java.net.URLDecoder;

public class DubboService {

    public static void main(String[] args) {
        String provider = "dubbo://172.17.40.229:20881/com.souche.sync.service.synchronous.SynchronousManageDataService?application=sync-souche&default.export=true&default.token=hello_souche_token&dubbo=2.5.8&export=true&generic=false&interface=com.souche.sync.service.synchronous.SynchronousManageDataService&pid=10335&revision=1.0.1-SNAPSHOT&side=provider&timestamp=1517826083746, "
                + "dubbo://172.17.40.208:20882/com.souche.sync.service.synchronous.SynchronousManageDataService?application=sync-souche&default.export=true&default.token=hello_souche_token&dubbo=2.5.8&export=true&generic=false&interface=com.souche.sync.service.synchronous.SynchronousManageDataService&pid=12324&revision=1.0.1-SNAPSHOT&side=provider&timestamp=1515220528637";

        String consumer = "consumer://172.17.40.229/com.souche.sync.service.synchronous.SynchronousManageDataService?application=sync-market&category=consumers&check=false&default.timeout=10000&dubbo=2.5.8&interface=com.souche.sync.service.synchronous.SynchronousManageDataService&pid=31757&revision=1.3.4&side=consumer&timestamp=1517465299567, "
                + "consumer://172.17.40.208/com.souche.sync.service.synchronous.SynchronousManageDataService?application=sync-market&category=consumers&check=false&default.timeout=10000&dubbo=2.5.8&interface=com.souche.sync.service.synchronous.SynchronousManageDataService&pid=3464&revision=1.3.3&side=consumer&timestamp=1516781679438";

    }
}
