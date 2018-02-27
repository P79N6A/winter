package com.panda.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SearchFinder {

	public static void main(String[] args) {
		//模块装配的时候能不在程序里动态指明，服务发现机制：通过外部配置确定接口的实现
		//具体操作：在META-INF/services/目录里创建一个以服务接口命名的文件.该文件里就是实现该服务接口的具体实现类.
		ServiceLoader<Search> sl = ServiceLoader.load(Search.class);
		Iterator<Search> iterator = sl.iterator();
		while(iterator.hasNext()){
			Search search = iterator.next();
			search.search();
		}
	}
}