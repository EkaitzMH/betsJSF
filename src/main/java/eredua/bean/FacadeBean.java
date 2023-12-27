package eredua.bean;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.UtilDate;
import dataAccess.DataAccess;

public class FacadeBean {
	private static FacadeBean singleton = new FacadeBean();
	
	private static BLFacade facadeInterface;
	DataAccess da = new DataAccess();
	
	private FacadeBean(){
		try { facadeInterface=new BLFacadeImplementation(da);
		//da.initializeDB();
		}
		catch (Exception e) {
			System.out.println("FacadeBean: negozioaren logika sortzean errorea: "+e.getMessage());
		}
	}
	
	public static BLFacade getBusinessLogic( ) {
		return facadeInterface;
	}
}