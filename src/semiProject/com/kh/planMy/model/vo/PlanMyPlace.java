package semiProject.com.kh.planMy.model.vo;

public class PlanMyPlace {  //MYPLAN_PLACE테이블

	private int mpPlaceNo;  //MP_PLACE_NO
	private int refMpNo;    //REF_MPNO
	private int placeNo;    //PLACE_NO
	private String status;  //STATUS
	
	public PlanMyPlace() {
		// TODO Auto-generated constructor stub
	}

	public int getMpPlaceNo() {
		return mpPlaceNo;
	}

	public void setMpPlaceNo(int mpPlaceNo) {
		this.mpPlaceNo = mpPlaceNo;
	}

	public int getRefMpNo() {
		return refMpNo;
	}

	public void setRefMpNo(int refMpNo) {
		this.refMpNo = refMpNo;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
