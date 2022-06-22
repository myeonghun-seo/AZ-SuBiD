package az.subid.util;

import com.google.protobuf.Internal;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class CmmUtil {

	// String 타입 Null 처리
	// 다른 기본 타입은 클래스를 사용하는 것을 권장.
	public static String nvl(String str, String chg_str) {

		String res;

		if (str == null) {
			res = chg_str;
		} else if (str.equals("")) {
			res = chg_str;
		} else {
			res = str;
		}

		return res;
	}

	// 오버로딩
	public static String nvl(String str){
		return nvl(str,"");
	}

	// Internal.IntList 타입 Null 처리
	public static Internal.IntList nvl(Internal.IntList intList, List<Integer> chg_IntList) {

		Internal.IntList res;

		if (intList == null) {
			res = (Internal.IntList) chg_IntList;
		} else if (intList.contains(0)) {
			res = (Internal.IntList) chg_IntList;
		} else {
			res = intList;
		}

		return res;
	}

	public static Internal.IntList nvl(Internal.IntList intList) { return nvl(intList, new LinkedList<>()); }

	// List<String> 타입 Null 처리
	public static List<String> nvl(List<String> list, List<String> chg_List) {

		List<String> res;

		if (list == null) {
			res = chg_List;
		} else if (list.contains("")) {
			res = chg_List;
		} else {
			res = list;
		}

		return res;
	}

	public static List<String> nvl(List<String> list) { return nvl(list, new LinkedList<>()); }

	public static String checked(String str, String com_str){
		if(str.equals(com_str)){
			return " checked";
		}else{
			return "";
		}
	}
	
	public static String checked(String[] str, String com_str){
		for(int i=0;i<str.length;i++){
			if(str[i].equals(com_str))
				return " checked";
		}
		return "";
	}
	
	public static String select(String str,String com_str){
		if(str.equals(com_str)){
			return " selected";
		}else{
			return "";
		}
	}
}
