package com.yrkj.elderlycareassess.bean;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

import com.yrkj.elderlycareassess.R;
import com.yrkj.util.db.DBMng;

public class CustomerData {
	public static final String TblName = "t_customer";
	
	public static final String SEX_MALE = "1";
	public static final String SEX_FEMALE = "2";
	
	
	public static final String Col_id = "id";
	public static final String Col_customername = "customername";
	public static final String Col_idnumber = "idnumber";
	public static final String Col_sex = "sex";
	public static final String Col_birthday = "birthday";
	public static final String Col_socialsecurity = "socialsecurity";
	public static final String Col_tel = "tel";
	public static final String Col_mobliephone = "mobliephone";
	public static final String Col_area = "area";
	public static final String Col_address = "address";
	public static final String Col_mail = "mail";
	public static final String Col_customerimg = "customerimg";
	public static final String Col_ethnic = "ethnic";
	public static final String Col_otherethnic = "otherethnic";
	public static final String Col_education = "education";
	public static final String Col_province = "province";
	public static final String Col_work = "work";
	public static final String Col_householdarea = "householdarea";
	public static final String Col_householdaddr = "householdaddr";
	public static final String Col_householdmail = "householdmail";
	public static final String Col_ismarry = "ismarry";
	public static final String Col_proxyname = "proxyname";
	public static final String Col_proxyrelation = "proxyrelation";
	public static final String Col_proxytel = "proxytel";
	public static final String Col_proxyphone = "proxyphone";
	public static final String Col_proxyarea = "proxyarea";
	public static final String Col_proxyaddr = "proxyaddr";
	public static final String Col_proxymail = "proxymail";
	public static final String Col_economicstatus = "economicstatus";
	public static final String Col_livestatus = "livestatus";
	public static final String Col_housestatus = "housestatus";
	public static final String Col_helpstatus = "helpstatus";
	public static final String Col_helper = "helper";
	public static final String Col_otherhelper = "otherhelper";
	public static final String Col_medicalstatus = "medicalstatus";
	public static final String Col_othermedical = "othermedical";
	public static final String Col_adduser = "adduser";
	public static final String Col_addtime = "addtime";
	public static final String Col_isok = "isok";
	public static final String Col_assessnum = "assessnum";
	public static final String Col_firstassessdate = "firstassessdate";
	public static final String Col_curassesstype = "curassesstype";
	public static final String Col_curassessstatus = "curassessstatus";
	public static final String Col_unitid = "unitid";

	
	public static String[] getColumnColl(){
		return new String[]{
				Col_id,
				Col_customername,
				Col_idnumber,
				Col_sex,
				Col_birthday,
				Col_socialsecurity,
				Col_tel,
				Col_mobliephone,
				Col_area,
				Col_address,
				Col_mail,
				Col_customerimg,
				Col_ethnic,
				Col_otherethnic,
				Col_education,
				Col_province,
				Col_work,
				Col_householdarea,
				Col_householdaddr,
				Col_householdmail,
				Col_ismarry,
				Col_proxyname,
				Col_proxyrelation,
				Col_proxytel,
				Col_proxyphone,
				Col_proxyarea,
				Col_proxyaddr,
				Col_proxymail,
				Col_economicstatus,
				Col_livestatus,
				Col_housestatus,
				Col_helpstatus,
				Col_helper,
				Col_otherhelper,
				Col_medicalstatus,
				Col_othermedical,
				Col_adduser,
				Col_addtime,
				Col_isok,
				Col_assessnum,
				Col_firstassessdate,
				Col_curassesstype,
				Col_curassessstatus,
				Col_unitid
				};
	}
	
	public String id = "";
	public String customername = "";
	public String idnumber = "";
	public String sex = "";
	public String birthday = "";
	public String socialsecurity = "";
	public String tel = "";
	public String mobliephone = "";
	public String area = "";
	public String address = "";
	public String mail = "";
	public String customerimg = "";
	public String ethnic = "";
	public String otherethnic = "";
	public String education = "";
	public String province = "";
	public String work = "";
	public String householdarea = "";
	public String householdaddr = "";
	public String householdmail = "";
	public String ismarry = "";
	public String proxyname = "";
	public String proxyrelation = "";
	public String proxytel = "";
	public String proxyphone = "";
	public String proxyarea = "";
	public String proxyaddr = "";
	public String proxymail = "";
	public String economicstatus = "";
	public String livestatus = "";
	public String housestatus = "";
	public String helpstatus = "";
	public String helper = "";
	public String otherhelper = "";
	public String medicalstatus = "";
	public String othermedical = "";
	public String adduser = "";
	public String addtime = "";
	public String isok = "";
	public String assessnum = "";
	public String firstassessdate = "";
	public String curassesstype = "";
	public String curassessstatus = "";
	public String unitid = "";

	
	
	
	public static CustomerData convertDataToModule(Cursor c){
		CustomerData item = new CustomerData();
		item.id = DBMng.GetDataString(c, Col_id);
		item.customername = DBMng.GetDataString(c, Col_customername);
		item.idnumber = DBMng.GetDataString(c, Col_idnumber);
		item.sex = DBMng.GetDataString(c, Col_sex);
		item.birthday = DBMng.GetDataString(c, Col_birthday);
		item.socialsecurity = DBMng.GetDataString(c, Col_socialsecurity);
		item.tel = DBMng.GetDataString(c, Col_tel);
		item.mobliephone = DBMng.GetDataString(c, Col_mobliephone);
		item.area = DBMng.GetDataString(c, Col_area);
		item.address = DBMng.GetDataString(c, Col_address);
		item.mail = DBMng.GetDataString(c, Col_mail);
		item.customerimg = DBMng.GetDataString(c, Col_customerimg);
		item.ethnic = DBMng.GetDataString(c, Col_ethnic);
		item.otherethnic = DBMng.GetDataString(c, Col_otherethnic);
		item.education = DBMng.GetDataString(c, Col_education);
		item.province = DBMng.GetDataString(c, Col_province);
		item.work = DBMng.GetDataString(c, Col_work);
		item.householdarea = DBMng.GetDataString(c, Col_householdarea);
		item.householdaddr = DBMng.GetDataString(c, Col_householdaddr);
		item.householdmail = DBMng.GetDataString(c, Col_householdmail);
		item.ismarry = DBMng.GetDataString(c, Col_ismarry);
		item.proxyname = DBMng.GetDataString(c, Col_proxyname);
		item.proxyrelation = DBMng.GetDataString(c, Col_proxyrelation);
		item.proxytel = DBMng.GetDataString(c, Col_proxytel);
		item.proxyphone = DBMng.GetDataString(c, Col_proxyphone);
		item.proxyarea = DBMng.GetDataString(c, Col_proxyarea);
		item.proxyaddr = DBMng.GetDataString(c, Col_proxyaddr);
		item.proxymail = DBMng.GetDataString(c, Col_proxymail);
		item.economicstatus = DBMng.GetDataString(c, Col_economicstatus);
		item.livestatus = DBMng.GetDataString(c, Col_livestatus);
		item.housestatus = DBMng.GetDataString(c, Col_housestatus);
		item.helpstatus = DBMng.GetDataString(c, Col_helpstatus);
		item.helper = DBMng.GetDataString(c, Col_helper);
		item.otherhelper = DBMng.GetDataString(c, Col_otherhelper);
		item.medicalstatus = DBMng.GetDataString(c, Col_medicalstatus);
		item.othermedical = DBMng.GetDataString(c, Col_othermedical);
		item.adduser = DBMng.GetDataString(c, Col_adduser);
		item.addtime = DBMng.GetDataString(c, Col_addtime);
		item.isok = DBMng.GetDataString(c, Col_isok);
		item.assessnum = DBMng.GetDataString(c, Col_assessnum);
		item.firstassessdate = DBMng.GetDataString(c, Col_firstassessdate);
		item.curassesstype = DBMng.GetDataString(c, Col_curassesstype);
		item.curassessstatus = DBMng.GetDataString(c, Col_curassessstatus);
		item.unitid = DBMng.GetDataString(c, Col_unitid);
		return item;
	}
	
	
	public static String getSexDesc(String s){
		String defineS = "";
		
		if(s.equals(SEX_MALE)){
			defineS = "��";
		}else if(s.equals(SEX_FEMALE)){
			defineS = "Ů";
		}
		return defineS;
		
	}
	
	
	public static String getEducationDesc(int id){
		String defineS = "";
		if(false){}
		else if(id == R.id.menuEducation1){ defineS = "��ä";}
		else if(id == R.id.menuEducation2){ defineS = "ССѧ";}
		else if(id == R.id.menuEducation3){ defineS = "����";}
		else if(id == R.id.menuEducation4){ defineS = "����";}
		else if(id == R.id.menuEducation5){ defineS = "��ר";}
		else if(id == R.id.menuEducation6){ defineS = "���Ƽ�����";}

		return defineS;
	}
	public static String getEducationId(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("��ä")){defineS = "1";}
		else if(s.equals("ССѧ")){defineS = "2";}
		else if(s.equals("����")){defineS = "3";}
		else if(s.equals("����")){defineS = "4";}
		else if(s.equals("��ר")){defineS = "5";}
		else if(s.equals("���Ƽ�����")){defineS = "6";}

		return defineS;
	}
	public static String getEducationDesc(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("1")){defineS = "��ä";}
		else if(s.equals("2")){defineS = "ССѧ";}
		else if(s.equals("3")){defineS = "����";}
		else if(s.equals("4")){defineS = "����";}
		else if(s.equals("5")){defineS = "��ר";}
		else if(s.equals("6")){defineS = "���Ƽ�����";}
		else{defineS = "��ѡ��";}

		return defineS;
	}
	
	
	public static String getEthnicDesc(int id){
		String defineS = "";
		if(false){}
		else if(id == R.id.menuEthnic1){ defineS = "����";}
		else if(id == R.id.menuEthnic2){ defineS = "׳��";}
		else if(id == R.id.menuEthnic3){ defineS = "����";}
		else if(id == R.id.menuEthnic4){ defineS = "����";}
		else if(id == R.id.menuEthnic5){ defineS = "ά�����";}
		else if(id == R.id.menuEthnic6){ defineS = "����";}
		else if(id == R.id.menuEthnic7){ defineS = "����";}
		else if(id == R.id.menuEthnic8){ defineS = "������";}
		else if(id == R.id.menuEthnic9){ defineS = "����";}
		else if(id == R.id.menuEthnic10){ defineS = "�ɹ���";}
		else if(id == R.id.menuEthnic11){ defineS = "����";}
		else if(id == R.id.menuEthnic12){ defineS = "������";}
		else if(id == R.id.menuEthnic13){ defineS = "����";}
		else if(id == R.id.menuEthnic14){ defineS = "����";}
		else if(id == R.id.menuEthnic15){ defineS = "������";}
		else if(id == R.id.menuEthnic16){ defineS = "������";}
		else if(id == R.id.menuEthnic17){ defineS = "����";}
		else if(id == R.id.menuEthnic18){ defineS = "��������";}
		else if(id == R.id.menuEthnic19){ defineS = "����";}
		else if(id == R.id.menuEthnic20){ defineS = "���";}
		else if(id == R.id.menuEthnic21){ defineS = "������";}
		else if(id == R.id.menuEthnic22){ defineS = "������";}
		else if(id == R.id.menuEthnic23){ defineS = "������";}
		else if(id == R.id.menuEthnic24){ defineS = "������";}
		else if(id == R.id.menuEthnic25){ defineS = "����";}
		else if(id == R.id.menuEthnic26){ defineS = "ˮ��";}
		else if(id == R.id.menuEthnic27){ defineS = "������";}
		else if(id == R.id.menuEthnic28){ defineS = "Ǽ��";}
		else if(id == R.id.menuEthnic29){ defineS = "����";}
		else if(id == R.id.menuEthnic30){ defineS = "������";}
		else if(id == R.id.menuEthnic31){ defineS = "������";}
		else if(id == R.id.menuEthnic32){ defineS = "�¶�������";}
		else if(id == R.id.menuEthnic33){ defineS = "������";}
		else if(id == R.id.menuEthnic34){ defineS = "���Ӷ���";}
		else if(id == R.id.menuEthnic35){ defineS = "������";}
		else if(id == R.id.menuEthnic36){ defineS = "������";}
		else if(id == R.id.menuEthnic37){ defineS = "ë����";}
		else if(id == R.id.menuEthnic38){ defineS = "��������";}
		else if(id == R.id.menuEthnic39){ defineS = "������";}
		else if(id == R.id.menuEthnic40){ defineS = "������";}
		else if(id == R.id.menuEthnic41){ defineS = "ŭ��";}
		else if(id == R.id.menuEthnic42){ defineS = "���¿���";}
		else if(id == R.id.menuEthnic43){ defineS = "����";}
		else if(id == R.id.menuEthnic44){ defineS = "��ŵ��";}
		else if(id == R.id.menuEthnic45){ defineS = "�°���";}
		else if(id == R.id.menuEthnic46){ defineS = "������";}
		else if(id == R.id.menuEthnic47){ defineS = "����˹��";}
		else if(id == R.id.menuEthnic48){ defineS = "ԣ����";}
		else if(id == R.id.menuEthnic49){ defineS = "���α����";}
		else if(id == R.id.menuEthnic50){ defineS = "�Ű���";}
		else if(id == R.id.menuEthnic51){ defineS = "���״���";}
		else if(id == R.id.menuEthnic52){ defineS = "������";}
		else if(id == R.id.menuEthnic53){ defineS = "������";}
		else if(id == R.id.menuEthnic54){ defineS = "��ɽ��";}
		else if(id == R.id.menuEthnic55){ defineS = "�����";}
		else if(id == R.id.menuEthnic56){ defineS = "��������";}
		else if(id == R.id.menuEthnic57){ defineS = "����";}

		
		return defineS;
	}
	public static String getEthnicId(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("����")){defineS = "1";}
		else if(s.equals("׳��")){defineS = "2";}
		else if(s.equals("����")){defineS = "3";}
		else if(s.equals("����")){defineS = "4";}
		else if(s.equals("ά�����")){defineS = "5";}
		else if(s.equals("����")){defineS = "6";}
		else if(s.equals("����")){defineS = "7";}
		else if(s.equals("������")){defineS = "8";}
		else if(s.equals("����")){defineS = "9";}
		else if(s.equals("�ɹ���")){defineS = "10";}
		else if(s.equals("����")){defineS = "11";}
		else if(s.equals("������")){defineS = "12";}
		else if(s.equals("����")){defineS = "13";}
		else if(s.equals("����")){defineS = "14";}
		else if(s.equals("������")){defineS = "15";}
		else if(s.equals("������")){defineS = "16";}
		else if(s.equals("����")){defineS = "17";}
		else if(s.equals("��������")){defineS = "18";}
		else if(s.equals("����")){defineS = "19";}
		else if(s.equals("���")){defineS = "20";}
		else if(s.equals("������")){defineS = "21";}
		else if(s.equals("������")){defineS = "22";}
		else if(s.equals("������")){defineS = "23";}
		else if(s.equals("������")){defineS = "24";}
		else if(s.equals("����")){defineS = "25";}
		else if(s.equals("ˮ��")){defineS = "26";}
		else if(s.equals("������")){defineS = "27";}
		else if(s.equals("Ǽ��")){defineS = "28";}
		else if(s.equals("����")){defineS = "29";}
		else if(s.equals("������")){defineS = "30";}
		else if(s.equals("������")){defineS = "31";}
		else if(s.equals("�¶�������")){defineS = "32";}
		else if(s.equals("������")){defineS = "33";}
		else if(s.equals("���Ӷ���")){defineS = "34";}
		else if(s.equals("������")){defineS = "35";}
		else if(s.equals("������")){defineS = "36";}
		else if(s.equals("ë����")){defineS = "37";}
		else if(s.equals("��������")){defineS = "38";}
		else if(s.equals("������")){defineS = "39";}
		else if(s.equals("������")){defineS = "40";}
		else if(s.equals("ŭ��")){defineS = "41";}
		else if(s.equals("���¿���")){defineS = "42";}
		else if(s.equals("����")){defineS = "43";}
		else if(s.equals("��ŵ��")){defineS = "44";}
		else if(s.equals("�°���")){defineS = "45";}
		else if(s.equals("������")){defineS = "46";}
		else if(s.equals("����˹��")){defineS = "47";}
		else if(s.equals("ԣ����")){defineS = "48";}
		else if(s.equals("���α����")){defineS = "49";}
		else if(s.equals("�Ű���")){defineS = "50";}
		else if(s.equals("���״���")){defineS = "51";}
		else if(s.equals("������")){defineS = "52";}
		else if(s.equals("������")){defineS = "53";}
		else if(s.equals("��ɽ��")){defineS = "54";}
		else if(s.equals("�����")){defineS = "55";}
		else if(s.equals("��������")){defineS = "56";}
		else if(s.equals("����")){defineS = "57";}

		
		return defineS;
	}
	public static String getEthnicDesc(String s){
		String defineS = "";
		
		if(false){}
		else if(s.equals("1")){defineS = "����";}
		else if(s.equals("2")){defineS = "׳��";}
		else if(s.equals("3")){defineS = "����";}
		else if(s.equals("4")){defineS = "����";}
		else if(s.equals("5")){defineS = "ά�����";}
		else if(s.equals("6")){defineS = "����";}
		else if(s.equals("7")){defineS = "����";}
		else if(s.equals("8")){defineS = "������";}
		else if(s.equals("9")){defineS = "����";}
		else if(s.equals("10")){defineS = "�ɹ���";}
		else if(s.equals("11")){defineS = "����";}
		else if(s.equals("12")){defineS = "������";}
		else if(s.equals("13")){defineS = "����";}
		else if(s.equals("14")){defineS = "����";}
		else if(s.equals("15")){defineS = "������";}
		else if(s.equals("16")){defineS = "������";}
		else if(s.equals("17")){defineS = "����";}
		else if(s.equals("18")){defineS = "��������";}
		else if(s.equals("19")){defineS = "����";}
		else if(s.equals("20")){defineS = "���";}
		else if(s.equals("21")){defineS = "������";}
		else if(s.equals("22")){defineS = "������";}
		else if(s.equals("23")){defineS = "������";}
		else if(s.equals("24")){defineS = "������";}
		else if(s.equals("25")){defineS = "����";}
		else if(s.equals("26")){defineS = "ˮ��";}
		else if(s.equals("27")){defineS = "������";}
		else if(s.equals("28")){defineS = "Ǽ��";}
		else if(s.equals("29")){defineS = "����";}
		else if(s.equals("30")){defineS = "������";}
		else if(s.equals("31")){defineS = "������";}
		else if(s.equals("32")){defineS = "�¶�������";}
		else if(s.equals("33")){defineS = "������";}
		else if(s.equals("34")){defineS = "���Ӷ���";}
		else if(s.equals("35")){defineS = "������";}
		else if(s.equals("36")){defineS = "������";}
		else if(s.equals("37")){defineS = "ë����";}
		else if(s.equals("38")){defineS = "��������";}
		else if(s.equals("39")){defineS = "������";}
		else if(s.equals("40")){defineS = "������";}
		else if(s.equals("41")){defineS = "ŭ��";}
		else if(s.equals("42")){defineS = "���¿���";}
		else if(s.equals("43")){defineS = "����";}
		else if(s.equals("44")){defineS = "��ŵ��";}
		else if(s.equals("45")){defineS = "�°���";}
		else if(s.equals("46")){defineS = "������";}
		else if(s.equals("47")){defineS = "����˹��";}
		else if(s.equals("48")){defineS = "ԣ����";}
		else if(s.equals("49")){defineS = "���α����";}
		else if(s.equals("50")){defineS = "�Ű���";}
		else if(s.equals("51")){defineS = "���״���";}
		else if(s.equals("52")){defineS = "������";}
		else if(s.equals("53")){defineS = "������";}
		else if(s.equals("54")){defineS = "��ɽ��";}
		else if(s.equals("55")){defineS = "�����";}
		else if(s.equals("56")){defineS = "��������";}
		else if(s.equals("57")){defineS = "����";}
		else{defineS = "��ѡ��";}
		
		return defineS;
	}

	
	public static String getProvinceDesc(int id) {
		String defineS = "";
		
		if(false){}	
		else if(id == R.id.menuProvince1){ defineS = "������";}
		else if(id == R.id.menuProvince2){ defineS = "�Ϻ���";}
		else if(id == R.id.menuProvince3){ defineS = "�����";}
		else if(id == R.id.menuProvince4){ defineS = "������";}
		else if(id == R.id.menuProvince5){ defineS = "����";}
		else if(id == R.id.menuProvince6){ defineS = "���ɹ�";}
		else if(id == R.id.menuProvince7){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince8){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince9){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince10){ defineS = "�㶫ʡ";}
		else if(id == R.id.menuProvince11){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince12){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince13){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince14){ defineS = "�ӱ�ʡ";}
		else if(id == R.id.menuProvince15){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince16){ defineS = "������";}
		else if(id == R.id.menuProvince17){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince18){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince19){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince20){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince21){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince22){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince23){ defineS = "�ຣʡ";}
		else if(id == R.id.menuProvince24){ defineS = "ɽ��ʡ";}
		else if(id == R.id.menuProvince25){ defineS = "ɽ��ʡ";}
		else if(id == R.id.menuProvince26){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince27){ defineS = "�Ĵ�ʡ";}
		else if(id == R.id.menuProvince28){ defineS = "����ʡ";}
		else if(id == R.id.menuProvince29){ defineS = "�㽭ʡ";}
		else if(id == R.id.menuProvince30){ defineS = "����";}
		else if(id == R.id.menuProvince31){ defineS = "�½�";}
		else if(id == R.id.menuProvince32){ defineS = "�۰�̨";}

		
		return defineS;
	}
	public static String getProvinceId(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("������")){defineS = "1";}
		else if(s.equals("�Ϻ���")){defineS = "2";}
		else if(s.equals("�����")){defineS = "3";}
		else if(s.equals("������")){defineS = "4";}
		else if(s.equals("����")){defineS = "5";}
		else if(s.equals("���ɹ�")){defineS = "6";}
		else if(s.equals("����ʡ")){defineS = "7";}
		else if(s.equals("����ʡ")){defineS = "8";}
		else if(s.equals("����ʡ")){defineS = "9";}
		else if(s.equals("�㶫ʡ")){defineS = "10";}
		else if(s.equals("����ʡ")){defineS = "11";}
		else if(s.equals("����ʡ")){defineS = "12";}
		else if(s.equals("����ʡ")){defineS = "13";}
		else if(s.equals("�ӱ�ʡ")){defineS = "14";}
		else if(s.equals("����ʡ")){defineS = "15";}
		else if(s.equals("������")){defineS = "16";}
		else if(s.equals("����ʡ")){defineS = "17";}
		else if(s.equals("����ʡ")){defineS = "18";}
		else if(s.equals("����ʡ")){defineS = "19";}
		else if(s.equals("����ʡ")){defineS = "20";}
		else if(s.equals("����ʡ")){defineS = "21";}
		else if(s.equals("����ʡ")){defineS = "22";}
		else if(s.equals("�ຣʡ")){defineS = "23";}
		else if(s.equals("ɽ��ʡ")){defineS = "24";}
		else if(s.equals("ɽ��ʡ")){defineS = "25";}
		else if(s.equals("����ʡ")){defineS = "26";}
		else if(s.equals("�Ĵ�ʡ")){defineS = "27";}
		else if(s.equals("����ʡ")){defineS = "28";}
		else if(s.equals("�㽭ʡ")){defineS = "29";}
		else if(s.equals("����")){defineS = "30";}
		else if(s.equals("�½�")){defineS = "31";}
		else if(s.equals("�۰�̨")){defineS = "32";}

		
		return defineS;
	}

	public static String getMarryDesc(int id) {
		String defineS = "";
		
		if(false){}
		else if(id == R.id.menuIsMarry1){ defineS = "δ��";}
		else if(id == R.id.menuIsMarry2){ defineS = "�ѻ�";}
		else if(id == R.id.menuIsMarry3){ defineS = "ɥż";}
		else if(id == R.id.menuIsMarry4){ defineS = "����";}

		return defineS;
	}
	public static String getMarryId(String s){
		String defineS = "";
		if(false){}
		else if(s.equals("δ��")){defineS = "1";}
		else if(s.equals("�ѻ�")){defineS = "2";}
		else if(s.equals("ɥż")){defineS = "3";}
		else if(s.equals("����")){defineS = "4";}
		return defineS;
	}
	public static String getMarryDesc(String s) {
		String defineS = "";
		
		if(false){}
		else if(s.equals("1")){defineS = "δ��";}
		else if(s.equals("2")){defineS = "�ѻ�";}
		else if(s.equals("3")){defineS = "ɥż";}
		else if(s.equals("4")){defineS = "����";}
		else{defineS = "��ѡ��";}


		return defineS;
	}


	public static String getRelationDesc(int id) {
		String defineS = "";
		
		if(false){}
		else if(id == R.id.menuRelation1){ defineS = "����";}
		else if(id == R.id.menuRelation2){ defineS = "��ĸ";}
		else if(id == R.id.menuRelation3){ defineS = "��Ů";}
		else if(id == R.id.menuRelation4){ defineS = "�ֵܽ���";}
		else if(id == R.id.menuRelation5){ defineS = "�游ĸ";}
		else if(id == R.id.menuRelation6){ defineS = "���游ĸ";}
		else if(id == R.id.menuRelation7){ defineS = "����Ů";}
		else if(id == R.id.menuRelation8){ defineS = "������Ů";}
		else if(id == R.id.menuRelation9){ defineS = "��ϱ�͹���";}
		else if(id == R.id.menuProvince10){ defineS = "Ů��������ĸ";}
		else if(id == R.id.menuProvince11){ defineS = "����";}
		else if(id == R.id.menuProvince12){ defineS = "����";}
		else if(id == R.id.menuProvince13){ defineS = "��ĸ";}
		else if(id == R.id.menuProvince14){ defineS = "��";}
		else if(id == R.id.menuProvince15){ defineS = "����";}
		else if(id == R.id.menuProvince16){ defineS = "ֶ��Ů";}
		else if(id == R.id.menuProvince17){ defineS = "����Ů";}
		else if(id == R.id.menuProvince18){ defineS = "���ֵ����";}
		else if(id == R.id.menuProvince19){ defineS = "���ֵ����";}

		return defineS;
	}
	public static String getRelationId(String s){
		String defineS = "";
		
		if(false){}
		else if(s.equals("����")){defineS = "1";}
		else if(s.equals("��ĸ")){defineS = "2";}
		else if(s.equals("��Ů")){defineS = "3";}
		else if(s.equals("�ֵܽ���")){defineS = "4";}
		else if(s.equals("�游ĸ")){defineS = "5";}
		else if(s.equals("���游ĸ")){defineS = "6";}
		else if(s.equals("����Ů")){defineS = "7";}
		else if(s.equals("������Ů")){defineS = "8";}
		else if(s.equals("��ϱ�͹���")){defineS = "9";}
		else if(s.equals("Ů��������ĸ")){defineS = "10";}
		else if(s.equals("����")){defineS = "11";}
		else if(s.equals("����")){defineS = "12";}
		else if(s.equals("��ĸ")){defineS = "13";}
		else if(s.equals("��")){defineS = "14";}
		else if(s.equals("����")){defineS = "15";}
		else if(s.equals("ֶ��Ů")){defineS = "16";}
		else if(s.equals("����Ů")){defineS = "17";}
		else if(s.equals("���ֵ����")){defineS = "18";}
		else if(s.equals("���ֵ����")){defineS = "19";}
		return defineS;
	}
	public static String getRelationDesc(String s) {
		String defineS = "";
		
		if(false){}
		else if(s.equals("1")){defineS = "����";}
		else if(s.equals("2")){defineS = "��ĸ";}
		else if(s.equals("3")){defineS = "��Ů";}
		else if(s.equals("4")){defineS = "�ֵܽ���";}
		else if(s.equals("5")){defineS = "�游ĸ";}
		else if(s.equals("6")){defineS = "���游ĸ";}
		else if(s.equals("7")){defineS = "����Ů";}
		else if(s.equals("8")){defineS = "������Ů";}
		else if(s.equals("9")){defineS = "��ϱ�͹���";}
		else if(s.equals("10")){defineS = "Ů��������ĸ";}
		else if(s.equals("11")){defineS = "����";}
		else if(s.equals("12")){defineS = "����";}
		else if(s.equals("13")){defineS = "��ĸ";}
		else if(s.equals("14")){defineS = "��";}
		else if(s.equals("15")){defineS = "����";}
		else if(s.equals("16")){defineS = "ֶ��Ů";}
		else if(s.equals("17")){defineS = "����Ů";}
		else if(s.equals("18")){defineS = "���ֵ����";}
		else if(s.equals("19")){defineS = "���ֵ����";}

		return defineS;
	}


	public static ContentValues getContentValues(CustomerData data) {
		ContentValues values = new ContentValues();
		
//		values.put(Col_id,data.id);
		values.put(Col_customername,data.customername);
		values.put(Col_idnumber,data.idnumber);
		values.put(Col_sex,data.sex);
		values.put(Col_birthday,data.birthday);
		values.put(Col_socialsecurity,data.socialsecurity);
		values.put(Col_tel,data.tel);
		values.put(Col_mobliephone,data.mobliephone);
		values.put(Col_area,data.area);
		values.put(Col_address,data.address);
		values.put(Col_mail,data.mail);
		values.put(Col_customerimg,data.customerimg);
		values.put(Col_ethnic,data.ethnic);
		values.put(Col_otherethnic,data.otherethnic);
		values.put(Col_education,data.education);
		values.put(Col_province,data.province);
		values.put(Col_work,data.work);
		values.put(Col_householdarea,data.householdarea);
		values.put(Col_householdaddr,data.householdaddr);
		values.put(Col_householdmail,data.householdmail);
		values.put(Col_ismarry,data.ismarry);
		values.put(Col_proxyname,data.proxyname);
		values.put(Col_proxyrelation,data.proxyrelation);
		values.put(Col_proxytel,data.proxytel);
		values.put(Col_proxyphone,data.proxyphone);
		values.put(Col_proxyarea,data.proxyarea);
		values.put(Col_proxyaddr,data.proxyaddr);
		values.put(Col_proxymail,data.proxymail);
		values.put(Col_economicstatus,data.economicstatus);
		values.put(Col_livestatus,data.livestatus);
		values.put(Col_housestatus,data.housestatus);
		values.put(Col_helpstatus,data.helpstatus);
		values.put(Col_helper,data.helper);
		values.put(Col_otherhelper,data.otherhelper);
		values.put(Col_medicalstatus,data.medicalstatus);
		values.put(Col_othermedical,data.othermedical);
		values.put(Col_adduser,data.adduser);
		values.put(Col_addtime,data.addtime);
		values.put(Col_isok,data.isok);
		values.put(Col_assessnum,data.assessnum);
		values.put(Col_firstassessdate,data.firstassessdate);
		values.put(Col_curassesstype,data.curassesstype);
		values.put(Col_curassessstatus,data.curassessstatus);
		values.put(Col_unitid,data.unitid);

		
		return values;
	}
}
	
