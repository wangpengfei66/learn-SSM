package com.kaishengit.dao;

import com.kaishengit.entity.Commission;
import com.kaishengit.util.DbHelp;

public class CommissionDao {

	public void save(Commission commission) {
		String sql = "insert into t_sal (empid,investid,commission)values(?,?,?)";
		DbHelp.executeUpdate(sql, commission.getEmpId(),commission.getInvestId(),commission.getCommission());
	}

	public void delByInvestId(int investId) {
		String sql = "delete from t_sal where investid = ? ";
		DbHelp.executeUpdate(sql, investId);
	}

}
