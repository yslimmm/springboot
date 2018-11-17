package com.sist.hr;

public enum Level {
	//선언에 DB저장값,다음 단계의 레벨 정보 추가.
	GOLD(3,null),SILVER(2,GOLD),BASIC(1,SILVER);
	
	private final int value;
	private final Level next;
	
	
	Level(int value,Level next){
		this.value = value;
		this.next  = next;
	}
	
	public Level getNextLevel() {
		return next;
	}
	

	//DB에 입력
	public int intValue() {
		return value;
	}
	
	
	public static Level valueOf(int value) {
		switch(value) {
			case 1: return BASIC;
			case 2: return SILVER;
			case 3: return GOLD;
			default: throw new AssertionError("Unknown value:"+value);
		}
	}
	
	
}
