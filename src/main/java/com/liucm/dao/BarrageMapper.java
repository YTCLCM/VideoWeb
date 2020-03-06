package com.liucm.dao;

import com.liucm.bean.Barrage;

public interface BarrageMapper {
	public int insertOne(Barrage barrage);
	public int deleteOneByBarrageId(int barrageId);
}
