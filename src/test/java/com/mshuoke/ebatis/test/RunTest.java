package com.mshuoke.ebatis.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import com.mshuoke.ebatis.create.CreateExcel;
import com.mshuoke.ebatis.impl.Init;
import com.mshuoke.ebatis.pojo.ActionContext;
import com.mshuoke.ebatis.test.pojo.CreateExcelPOJO;
import com.mshuoke.ebatis.test.pojo.ImportPojo;

public class RunTest {
	
	// 计时
	public long a1;
	public long a2;
	
	/*
	 * 完全正确的数据（格式完全正确）
	 */
	@Test
	public void importTestAllRight() throws Exception {
		// =========== xlsx for sax
		runInit("exl/allright.xlsx", false);
		// =========== xls for usermodel
		runInit("exl/allright.xls", false);
	}
	
	/*
	 * 日期包含多种格式的（但单元格都是日期类型）
	 */
	@Test
	public void manyDateTest() throws Exception {
		// =========== xlsx for sax
		runInit("exl/manydate.xlsx", false);
		// =========== xls for usermodel
		runInit("exl/manydate.xls", false);
	}
	
	/*
	 * 仅包含空cell，空的将赋值为null
	 */
	@Test
	public void blankCellTest() throws Exception {
		// =========== xlsx for sax
		runInit("exl/blankcell.xlsx", false);
		// =========== xls for usermodel
		runInit("exl/blankcell.xls", false);
	}
	
	/*
	 * 空行测试
	 */
	@Test
	public void blankRowTest() throws Exception {
		// =========== xlsx for sax
		runInit("exl/blankrow.xlsx", false);
		// =========== xls for usermodel
		runInit("exl/blankrow.xls", false);
	}
	
	/*
	 * 多sheet测试（格式完全正确）
	 */
	@Test
	public void manyRightSheetTest() throws Exception {
		// =========== xlsx for sax
		runInit("exl/manyrightsheet.xlsx", false);
		// =========== xls for usermodel
		runInit("exl/manyrightsheet.xls", false);
	}
	
	/*
	 * 空的sheet包含（只包含表头或什么都没有）
	 */
	@Test
	public void manyBlankSheetTest() throws Exception {
		// =========== xlsx for sax
		runInit("exl/manyblanksheet.xlsx", false);
		// =========== xls for usermodel
		runInit("exl/manyblanksheet.xls", false);
	}
	
	/*
	 * run 程序
	 */
	public void runInit(String fileName, boolean distinct) throws Exception {
		InputStream inputStream = null;
		ActionContext<ImportPojo> act = null;
		Init<ImportPojo> init = null;
		a1 = System.currentTimeMillis();
		inputStream = new FileInputStream(fileName);
		init = new Init<ImportPojo>(inputStream, ImportPojo.class, distinct);
		act = init.start();
		printInfo(act);
		inputStream.close();
	}
	
	/**
	 * 打印信息结果
	 * @param act
	 */
	public void printInfo(ActionContext<ImportPojo> act) {
		System.out.println("全部信息如下 ===================================");
		System.out.println(act);
		a2 = System.currentTimeMillis() - a1;
		System.out.println("耗时（s）：" + a2 / 1000);
		System.out.println("耗时（ms）：" + a2);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//@Test
	public void createExcelTest() {
		
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream("exl/create.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Init<CreateExcelPOJO> init = new Init<CreateExcelPOJO>(inputStream, CreateExcelPOJO.class, true);
		ActionContext<CreateExcelPOJO> act = init.start();
		
		List<CreateExcelPOJO> list = act.getSheets().get(0).getInfo();
		
		CreateExcel<CreateExcelPOJO> c = new CreateExcel<CreateExcelPOJO>();
		try {
			c.create(list, "这是一个sheet名", new File("生成excel.xlsx"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
