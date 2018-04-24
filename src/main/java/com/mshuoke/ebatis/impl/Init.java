package com.mshuoke.ebatis.impl;

import java.io.InputStream;

import com.mshuoke.ebatis.pojo.ActionContext;

/**
 * 
 * 链入口
 * @author 杨硕
 *
 */
public class Init<T> {
	
	private ActionContext<T> act = new ActionContext<T>();
	
	private InputStream inp;
	
	private VerificationTable<T> ver;
	
	public Init(InputStream inp, Class<? extends T> t, boolean distinct) {
		super();
		this.inp = inp;
		act.setInputStream(inp);
		act.setObjects(t);
		act.setDistinct(distinct);
	}
	
	public ActionContext<T> start(){
		ver = new VerificationTable<T>();
		ver.prepare(act);
		return act;
	}
	
	public VerificationTable<T> getVer() {
		return ver;
	}

	public void setVer(VerificationTable<T> ver) {
		this.ver = ver;
	}

	public ActionContext<T> getAct() {
		return act;
	}

	public void setAct(ActionContext<T> act) {
		this.act = act;
	}

	public InputStream getInp() {
		return inp;
	}

	public void setInp(InputStream inp) {
		this.inp = inp;
	}
}
