package com.github.jucovschi.workflowy.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class UrlQueryString {
	private static final String DEFAULT_ENCODING = "UTF-8";

	public static String buildQueryString(final LinkedHashMap<String, Object> map) {
		try {
			final Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
			final StringBuilder sb = new StringBuilder(map.size() * 8);
			while (it.hasNext()) {
				final Map.Entry<String, Object> entry = it.next();
				final String key = entry.getKey();
				if (key != null) {
					sb.append(URLEncoder.encode(key, DEFAULT_ENCODING));
					sb.append("=");
					final Object value = entry.getValue();
					final String valueAsString = value != null ? URLEncoder.encode(value.toString(), DEFAULT_ENCODING) : "";
					sb.append(valueAsString);
					if (it.hasNext()) {
						sb.append("&");
					}
				} else {
					// Do what you want...for example:
					assert false : String.format("Null key in query map: %s", map.entrySet());
				}
			}
			return sb.toString();
		} catch (final UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public static String buildQueryStringMulti(final LinkedHashMap<String, List<Object>> map) {
		try {
			final StringBuilder sb = new StringBuilder(map.size() * 8);
			for (final Iterator<Entry<String, List<Object>>> mapIterator = map.entrySet().iterator(); mapIterator.hasNext();) {
				final Entry<String, List<Object>> entry = mapIterator.next();
				final String key = entry.getKey();
				if (key != null) {
					final String keyEncoded = URLEncoder.encode(key, DEFAULT_ENCODING);
					final List<Object> values = entry.getValue();
					sb.append(keyEncoded);
					sb.append("=");
					if (values != null) {
						for (final Iterator<Object> listIt = values.iterator(); listIt.hasNext();) {
							final Object valueObject = listIt.next();
							sb.append(valueObject != null ? URLEncoder.encode(valueObject.toString(), DEFAULT_ENCODING) : "");
							if (listIt.hasNext()) {
								sb.append("&");
								sb.append(keyEncoded);
								sb.append("=");
							}
						}
					}
					if (mapIterator.hasNext()) {
						sb.append("&");
					}
				} else {
					// Do what you want...for example:
					assert false : String.format("Null key in query map: %s", map.entrySet());
				}
			}
			return sb.toString();
		} catch (final UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

}