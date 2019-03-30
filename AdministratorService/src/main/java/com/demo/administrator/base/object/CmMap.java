package com.demo.administrator.base.object;

import java.io.Reader;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author hclee
 * framework 에서 공통으로 사용할 map 구조체
 */
public class CmMap extends HashMap<String, Object> implements Serializable {
	private static final long serialVersionUID = 1L;

	/*사용자 정의 method 영역*/
	public String clobToString ( oracle.sql.CLOB clob ) {
		StringBuilder	sbf		= new StringBuilder();
		Reader			rd		= null;
		char[]			buf		= new char[1024];
		int				readCnt	= 0;
		
		try {
			rd	= clob.getCharacterStream(0l);
			while ((readCnt = rd.read(buf, 0, 1024)) != -1 ) {
				sbf.append(buf, 0, readCnt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rd != null){
					rd.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sbf.toString();
	}
	
	public int getInt(String key) {
		int result = 0;
		
		try {
			result = Integer.parseInt(String.valueOf(super.get(key)));	
		} catch ( Exception e) {
			result = 0;
		}
		return result;
	}
	
	public int getInt(String key, int defaultValue) {
		int result = 0;
		
		try {
			result = Integer.parseInt(String.valueOf(super.get(key)));	
		} catch ( Exception e) {
			result = defaultValue;
		}
		return result;
	}
	
	public String getString(String key) {
		return (super.get(key) == null ? "" : String.valueOf(super.get(key)));
	}
	
	public String[] getStringArray(String key) {
		
		if (super.get(key) == null) 
			return null;
		
		if ( ! super.get(key).getClass().isArray() ) {
			String[] result = {""};
			result[0] = (String) super.get(key); 
			return result;
		}
		
		return (String[])super.get(key);
	}
	
	public String getString(String key, String defaultValue) {
		String result = "";
		
		try {
			result = (String)super.get(key);	
		} catch ( Exception e) {
			result = defaultValue;
		}
		
		return result;
	}
	
	public double getDouble(String key) {
		return Double.parseDouble(String.valueOf(super.get(key)));
	}
	
	public double getDouble(String key, double defaultValue) {
		double result = 0.0;
		
		try {
			result = Double.parseDouble((String)(super.get(key)));
		} catch ( Exception e) {
			result = defaultValue;
		}
		
		return result;
	}
	
	public boolean getBoolean(String key) {
		boolean result = false;
		
		try {
			result = (Boolean) super.get(key);
		} catch ( Exception e) {
			result = false;
		}
		
		return result;
	}
	
	/*이후 override 영역*/
	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public Object clone() {
		return super.clone();
	}

	@Override
	public Object compute(String key, BiFunction<? super String, ? super Object, ? extends Object> remappingFunction) {
		return super.compute(key, remappingFunction);
	}

	@Override
	public Object computeIfAbsent(String key, Function<? super String, ? extends Object> mappingFunction) {
		return super.computeIfAbsent(key, mappingFunction);
	}

	@Override
	public Object computeIfPresent(String key,
			BiFunction<? super String, ? super Object, ? extends Object> remappingFunction) {
		return super.computeIfPresent(key, remappingFunction);
	}

	@Override
	public boolean containsKey(Object key) {
		return super.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return super.containsValue(value);
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		return super.entrySet();
	}

	@Override
	public void forEach(BiConsumer<? super String, ? super Object> action) {
		super.forEach(action);
	}

	@Override
	public Object get(Object key) {
		return super.get(key);
	}

	@Override
	public Object getOrDefault(Object key, Object defaultValue) {
		return super.getOrDefault(key, defaultValue);
	}

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return super.keySet();
	}

	@Override
	public Object merge(String key, Object value,
			BiFunction<? super Object, ? super Object, ? extends Object> remappingFunction) {
		return super.merge(key, value, remappingFunction);
	}

	@Override
	public Object put(String key, Object value) {
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		super.putAll(m);
	}

	@Override
	public Object putIfAbsent(String key, Object value) {
		return super.putIfAbsent(key, value);
	}

	@Override
	public Object remove(Object key) {
		return super.remove(key);
	}

	@Override
	public boolean remove(Object key, Object value) {
		return super.remove(key, value);
	}

	@Override
	public Object replace(String key, Object value) {
		return super.replace(key, value);
	}

	@Override
	public boolean replace(String key, Object oldValue, Object newValue) {
		return super.replace(key, oldValue, newValue);
	}

	@Override
	public void replaceAll(BiFunction<? super String, ? super Object, ? extends Object> function) {
		super.replaceAll(function);
	}

	@Override
	public int size() {
		return super.size();
	}

	@Override
	public Collection<Object> values() {
		return super.values();
	}
	
}
