package com.speedy.exam.dao.key;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.speedy.exam.dao.BaseDao;
import com.speedy.exam.exception.KeyException;
import com.speedy.exam.model.key.TpKeyNumber;
import com.speedy.exam.model.key.TpKeyString;

/**
 * 获取系统主键的数据库访问接口
 */ 
@Repository
public class KeyDAO extends BaseDao  
{
	public long nextKey(String tableName) throws KeyException {
		return nextKey(tableName, 1);
	}

	public long nextKey(String tableName, int count) throws KeyException {

		try {
			/*
			 * jdbcConn = getConnection(); String sql = "update " +
			 * KeyConstants.KEY_TABLE_NAME +
			 * " set keyno=keyno+? where tabname=?"; ps =
			 * jdbcConn.prepareStatement(sql); ps.setLong(1, count);
			 * ps.setString(2,tableName); int rc = ps.executeUpdate();
			 */
			int rc = updateTpKeyNumber(count, tableName);
			// 如果不存在
			if (rc < 1) {
				/*
				 * closeResultSet(rs); closeStatement(ps); sql = "insert into "
				 * + KeyConstants.KEY_TABLE_NAME +
				 * " ( tabname, keyno) VALUES ( ?,?)"; ps =
				 * jdbcConn.prepareStatement(sql); ps.setString(1,tableName);
				 * ps.setLong(2, count); ps.executeUpdate(); closeStatement(ps);
				 */
				String tabname = tableName;
				int keyno = count;
				int tp = addTpKeyNumber(keyno, tabname);
			}

			/*
			 * sql = "select keyno from " + KeyConstants.KEY_TABLE_NAME +
			 * " where tabname=?"; ps = jdbcConn.prepareStatement(sql);
			 * ps.setString(1, tableName); rs = ps.executeQuery();
			 */
			List<TpKeyNumber> rs = getTpKeyNumber(tableName);

			for (TpKeyNumber tpKeyNumber : rs) {
				return (long) tpKeyNumber.getKeyno();
			}
			throw new KeyException("获取" + tableName + "主键发生错误");
		} catch (Exception e) {
			throw new KeyException(e);
		}

	}

	public long nextKey(String tableName, String curDate) throws KeyException {
		return nextKey(tableName, curDate, 1);
	}

	public long nextKey(String tableName, String curDate, int count)
			throws KeyException {

		try {
			/*
			 * jdbcConn = getConnection(); String sql = "update " +
			 * KeyConstants.CODE_TABLE_NAME +
			 * " set code=code+? where tabname=? and curdate=?"; ps =
			 * jdbcConn.prepareStatement(sql); ps.setInt(1, count);
			 * ps.setString(2,tableName); ps.setString(3, curDate); int rc =
			 * ps.executeUpdate();
			 */
			int rc = updateTpKeyString(count, tableName, curDate);
			// 如果不存在
			if (rc < 1) {
				/*
				 * closeResultSet(rs); closeStatement(ps); sql = "insert into "
				 * + KeyConstants.CODE_TABLE_NAME +
				 * " (tabname, curdate, code) VALUES (?,?,?)"; ps =
				 * jdbcConn.prepareStatement(sql); ps.setString(1,tableName);
				 * ps.setString(2,curDate); ps.setLong(3, count);
				 * ps.executeUpdate(); closeStatement(ps);
				 */
				addTpKeyString(count, tableName, curDate);
			}

			/*
			 * sql = "select code from " + KeyConstants.CODE_TABLE_NAME +
			 * " where tabname=? and curdate=?"; ps =
			 * jdbcConn.prepareStatement(sql); ps.setString(1, tableName);
			 * ps.setString(2,curDate); rs = ps.executeQuery();
			 */
			List<TpKeyString> rs = getTpKeyString(tableName, curDate);
			for (TpKeyString tpKeyString : rs) {
				return (long) tpKeyString.getCode();
			}
			throw new KeyException("获取" + tableName + "主键发生错误");
		} catch (Exception e) {
			throw new KeyException(e);
		}

	}

	public void clearKey(String curdate) throws KeyException {
		PreparedStatement ps = null;
		Connection jdbcConn = null;
		try {
			/*
			 * jdbcConn = getConnection(); String sql = "delete from " +
			 * KeyConstants.CODE_TABLE_NAME + " where curDate=?"; ps =
			 * jdbcConn.prepareStatement(sql); ps.setString(1,curdate);
			 * ps.executeUpdate();
			 */
			delTpKeyString(curdate);
		} catch (Exception e) {
			throw new KeyException(e);
		}

	}

	public String getNamespace() {
		// TODO Auto-generated method stub
		return "com.speedy.exam.dao.key.KeyDAO.";
	}

	public int updateTpKeyNumber(int count, String tabname) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("count", count);
		param.put("tabname", tabname);
		return sqlSessionMyExam.update(getNamespace() + "updateTpKeyNumber",
				param);
	}

	public int addTpKeyNumber(int keyno, String tabname) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("keyno", keyno);
		param.put("tabname", tabname);
		return sqlSessionMyExam.insert(getNamespace() + "addTpKeyNumber", param);
	}

	public List<TpKeyNumber> getTpKeyNumber(String tabname) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tabname", tabname);
		return sqlSessionMyExam.selectList(getNamespace() + "getTpKeyNumber",
				param);
	}

	public int updateTpKeyString(int code, String tabname, String curdate) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		param.put("tabname", tabname);
		param.put("curdate", curdate);
		return sqlSessionMyExam.update(getNamespace() + "updateTpKeyString", param);
	}

	public int addTpKeyString(int code, String tabname, String curdate) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		param.put("tabname", tabname);
		param.put("curdate", curdate);
		return sqlSessionMyExam.insert(getNamespace() + "addTpKeyString", param);
	}

	public List<TpKeyString> getTpKeyString(String tabname, String curdate) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tabname", tabname);
		param.put("curdate", curdate);
		return sqlSessionMyExam.selectList(getNamespace() + "getTpKeyString",
				param);
	}

	public int delTpKeyString(String curDate) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("curdate", curDate);
		return sqlSessionMyExam.delete(getNamespace() + "delTpKeyString", param);
	}
}
