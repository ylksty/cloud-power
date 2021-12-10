package com.bjpowernode;

import com.bjpowernode.config.MyConfig;
import com.bjpowernode.model.Goods;
import com.bjpowernode.service.GoodsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {

	public static void main(String[] args) throws Exception {

		//启动spring容器
		ApplicationContext context =
				new AnnotationConfigApplicationContext(MyConfig.class);

		//从spring容器中得到GoodsService对象
		GoodsService goodsService = context.getBean(GoodsService.class);

		Goods goods = goodsService.selectByPrimaryKey(1);
		System.out.println("查询结果：" + goods.getId() + "--" + goods.getName() + "--" + goods.getStore());
	}
}