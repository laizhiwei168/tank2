package com.cyws.tank.manager.dbutility;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Common<T> {
	public T Dt2Model(T obj, List dt) {
		if (dt.size() <= 0)
			return obj;
		Class type = obj.getClass();
		Field[] pis = type.getDeclaredFields();
		// List<String> colNameArr = new ArrayList<String>();

		Map map = (Map) dt.get(0);
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			for (int i = 0; i < pis.length; i++) {
				if (!pis[i].getName().equalsIgnoreCase(key.toString()))
					continue;
				SetValue(obj, pis[i], value);

			}
		}

		return obj;
	}

	// / <summary>
	// / 通过反射获得对象名称和自动增长ID
	// / </summary>
	// / <param name="obj">对象</param>
	// / <returns>返回string[0]类名，string[1]自增ID</returns>
	public String[] GetModelInfo(T obj) {
		String[] str = new String[3];
		Class type = obj.getClass();
		// Class[] args1 = new Class[1];
		// args1[0] = String.class;
		Method method = null;
		try {
			method = type.getMethod("ReturnAutoID", new Class[0]);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 通过反射执行ReturnAutoID方法，返回AutoID值
		String AutoID = null;
		try {
			AutoID = method.invoke(obj, new Object[0]).toString();
			str[0] = type.getSimpleName();
			str[1] = AutoID;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			method = type.getMethod("ReturnSeqName", new Class[0]);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			str[2] = method.invoke(obj, new Object[0]).toString();
		
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 返回该Obj的名称以及ReturnAutoID的值
		return str;
	}

	public void SetValue(T obj, Field field, Object value) {
		String dtname = field.getName().toLowerCase();
		String dttype = field.getType().getSimpleName().toString();
		Class cls = obj.getClass();
		Class[] args1 = new Class[1];
		Object[] obs = new Object[1];
		obs[0]=value;
		if (dttype.equalsIgnoreCase("int")) {

			args1[0] = int.class;
			if (value != null) {
				obs[0] =Integer.parseInt(value.toString()) ;
				
			}

		} else if (dttype.equalsIgnoreCase("integer")) {

			args1[0] = Integer.class;
			if (value != null) {
				obs[0] =Integer.parseInt(value.toString()) ;
				
			}

		} else if (dttype.equalsIgnoreCase("date")) {

			args1[0] = Date.class;
		

		} else if (dttype.equals("Long")) {

			args1[0] = Long.class;
			if (value != null) {
				obs[0] =Long.parseLong(value.toString()) ;
				
			}

		} else if (dttype.equals("long")) {

			args1[0] = long.class;
			if (value != null) {
				obs[0] =Long.parseLong(value.toString()) ;
				
			}

		} else if (dttype.equals("Double")) {

			args1[0] = Double.class;
			if (value != null) {
				obs[0] =Double.parseDouble(value.toString()) ;
				
			}
		} 
		else if (dttype.equals("double")) {

			args1[0] = double.class;
			if (value != null) {
				obs[0] =Double.parseDouble(value.toString()) ;
				
			}
		} 
		else if (dttype.equals("byte[]")) {

			args1[0] =byte[].class;
			if (value != null) {
				obs[0] =value ;
				
			}
		} else if (dttype.equalsIgnoreCase("string")) {

			args1[0] = String.class;
			
		} else {

			args1[0] = Object.class;
			
		}

		Method method = null;
		try {
			String tmp=dtname.substring(0,1).toUpperCase()+dtname.substring(1);
			method = cls.getMethod("set" + tmp, args1);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			
		
				method.invoke(obj, obs);
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object GetValue(T obj, Field field) {
		String dtname = field.getName().toLowerCase();
		String dttype = field.getType().getSimpleName().toString();
		Class cls = obj.getClass();
		Object object = null;

		Method method = null;
		try {
			String tmp=dtname.substring(0,1).toUpperCase()+dtname.substring(1);
			method = cls.getMethod("get" + tmp, new Class[0]);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			object = method.invoke(obj, new Object[0]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

	public List<T> Dt2List(List dt,Class c) {
		List<T> list = new ArrayList<T>();
		Iterator iterd = dt.iterator();

		while (iterd.hasNext()) {
			T t=null;
			try {
				t = (T) c.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			Map map = (Map) iterd.next();

			Field[] pis = t.getClass().getDeclaredFields();

			Iterator iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				for (int i = 0; i < pis.length; i++) {
					if (!pis[i].getName().equalsIgnoreCase(key.toString()))
						continue;
					SetValue(t, pis[i], value);

				}
			}

			list.add(t);
		}
		return list;
	}
	

}
