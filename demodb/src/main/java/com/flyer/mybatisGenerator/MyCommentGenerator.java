package com.flyer.mybatisGenerator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * Created by Administrator on 2016/10/9.
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
			StringBuilder sb = new StringBuilder();
			field.addJavaDocLine("/**");
			String remarks = introspectedColumn.getRemarks();
			String[] split = remarks.split("###");
			if(split != null){
				for (String s : split) {
					field.addJavaDocLine("* "+s);
				}
			}
			field.addJavaDocLine(" */");
		}
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
			method.addJavaDocLine("/**");
			method.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			method.addJavaDocLine(" */");
		}
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (introspectedColumn.getRemarks() != null && !introspectedColumn.getRemarks().equals("")) {
			method.addJavaDocLine("/**");
			method.addJavaDocLine(" * " + introspectedColumn.getRemarks());
			method.addJavaDocLine(" */");
		}
	}
}
