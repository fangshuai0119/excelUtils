package cn.zhuhongqing.excel;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.zhuhongqing.excel.convert.ExcelConvertException;
import cn.zhuhongqing.excel.exception.ShortCircuit;
import cn.zhuhongqing.excel.utils.GenericUtils;

public class ShortCircuitTest {

	@Test
	public void test2() throws Exception {

		Map<Class<?>, Object> constructorTypeAndParam = new HashMap<Class<?>, Object>();

		constructorTypeAndParam.put(Throwable.class, new RuntimeException());

		constructorTypeAndParam.put(String.class, "出错拉～");

		ExcelConvertException e = GenericUtils.autoCreateObject(
				ExcelConvertException.class, constructorTypeAndParam);

		System.out.println(e.getMessage());
	}

	@Test
	public void test3() throws Exception {

		NullPointerException nullPointerException = new NullPointerException(
				"空指针");
		ExcelConvertException excelConvertException = new ExcelConvertException(
				"类型异常");
		ShortCircuit shortCircuit = new ShortCircuit();
		shortCircuit.setShortCircuit(false);
		shortCircuit.addShortKey(nullPointerException);
		shortCircuit.setShortSize(3);

		shortCircuit.addException("转换异常");
		shortCircuit.addException(nullPointerException);
		shortCircuit.addException(excelConvertException);

		System.out.println(shortCircuit.hasException());
		System.out.println(shortCircuit.getExceptionMessage());

		// shortCircuit.getCause().printStackTrace();
		// shortCircuit.fillInStackTrace().printStackTrace();
		// System.out.println(shortCircuit.getStackTrace().length);
	}

	@Test
	public void test4() throws Exception {
		NullPointerException nullPointerException = new NullPointerException(
				"空指针");

		Constructor<ShortCircuit> constructor = ShortCircuit.class
				.getConstructor(Throwable.class);

		ShortCircuit shortCircuit = constructor
				.newInstance(nullPointerException);

		System.out.println(shortCircuit);
	}

	@Test
	public void test5() {

		NullPointerException nullPointerException = new NullPointerException(
				"空指针");

		ShortCircuit shortCircuit = new ShortCircuit();

		try {

			shortCircuit.addException(nullPointerException);
		} catch (ShortCircuit e) {
			System.out.println(e);
		}

		// System.out.println(shortCircuit.getExceptionMessage());

	}

	@Test
	public void test6() throws ShortCircuit {
		NullPointerException nullPointerException = new NullPointerException(
				"空指针");

		ShortCircuit s1 = new ShortCircuit("异常1", nullPointerException);
		ShortCircuit s2 = new ShortCircuit("异常2", s1);
		ShortCircuit s3 = new ShortCircuit("异常3", s2);

		try {
			throw s3;
		} catch (ShortCircuit e) {
			System.out.println(e);
		}
	}

	@Test
	public void test7() throws ShortCircuit {
		ShortCircuit shortCircuit = new ShortCircuit();

		NullPointerException nullPointerException = new NullPointerException(
				"空指针");

		ExcelConvertException dateException = new ExcelConvertException(
				"日期类型异常");

		ExcelConvertException numberException = new ExcelConvertException(
				"数字类型异常");

		shortCircuit.setShortCircuit(false);
		shortCircuit.setShortSize(20);
		shortCircuit.addShortKey("短路异常");
		shortCircuit.addShortKey(dateException);

		shortCircuit.addException(nullPointerException);
		shortCircuit.addException("短路异常");
		shortCircuit.addException(dateException);
		shortCircuit.addException(numberException);

	}

	@Test
	public void test8() {
		System.out.println("Hello Git");
	}
}
