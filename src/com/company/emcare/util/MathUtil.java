package com.company.emcare.util;

import java.math.BigDecimal;

public class MathUtil {
	public static float getFloatWith2(float number){
		BigDecimal b = new BigDecimal(number);  
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();  
	}
}
