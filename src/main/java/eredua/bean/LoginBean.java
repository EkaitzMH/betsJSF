package eredua.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.TypedQuery;

import businessLogic.BLFacade;
import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    transient BLFacade facadeBL;

    private int urtea;
    private int hilabetea;
    private int eguna;
    private Date data;
    private Event aukeratutakoGertaera;
    private Vector<Event> gertaerak;
    private Vector<Question> galderak;
    private String question;
    private int betMinimun;
    public LoginBean() {
        //this.facadeBL=FacadeBean.getBusinessLogic();
    }

    public Event getAukeratutakoGertaera() {
        return aukeratutakoGertaera;
    }

    public void setAukeratutakoGertaera(Event aukeratutakoGertaera) {
        this.aukeratutakoGertaera = aukeratutakoGertaera;
    }

    public Vector<Question> getGalderak() {
        return galderak;
    }

    public int getUrtea() {
        return urtea;
    }

    public void setUrtea(int urtea) {
        this.urtea = urtea;
    }

    public int getHilabetea() {
        return hilabetea;
    }

    public void setHilabetea(int hilabetea) {
        this.hilabetea = hilabetea;
    }

    public int getEguna() {
        return eguna;
    }

    public void setEguna(int eguna) {
        this.eguna = eguna;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Vector<Event> getGertaerak() {
        return gertaerak;
    }

    public void setGertaerak(Vector<Event> gertaerak) {
        this.gertaerak = gertaerak;
    }
    public String getQuestion() {
    	return this.question;
    }
    public void setQuestion(String question) {
    	this.question=question;
    }
    public int getBetMinimun() {
    	return this.betMinimun;
    }
    public void setBetMinimun(int betMinimun) {
    	this.betMinimun=betMinimun;
    }

    public void getEvents(){

        hilabetea = hilabetea - 1;
        this.facadeBL = FacadeBean.getBusinessLogic();
        ConfigXML c = ConfigXML.getInstance();
        this.data = UtilDate.newDate(urtea, hilabetea, eguna);
        Vector<Event> vecevents = this.facadeBL.getEvents(this.data);
        System.out.println(c.getDataBaseOpenMode());
        this.gertaerak = vecevents;
    }
    public void createQuestion() throws EventFinished, QuestionAlreadyExist {
    	this.facadeBL = FacadeBean.getBusinessLogic();
        ConfigXML c = ConfigXML.getInstance();
        this.data = UtilDate.newDate(urtea, hilabetea, eguna);
        facadeBL.createQuestion(this.aukeratutakoGertaera, this.question, this.betMinimun);
    }
    public void getAllEvents() {
    	this.facadeBL = FacadeBean.getBusinessLogic();
        ConfigXML c = ConfigXML.getInstance();
        Vector<Event> vecevents = this.facadeBL.getEvents( UtilDate.newDate(2024, 0, 1));
        Vector<Event> vecevents2 = this.facadeBL.getEvents( UtilDate.newDate(2024, 0, 17));
        Vector<Event> vecevents3 = this.facadeBL.getEvents( UtilDate.newDate(2024, 0, 28));
        Vector<Event> erantzuna= new Vector<Event>();
        erantzuna.addAll(vecevents);
        erantzuna.addAll(vecevents2);
        erantzuna.addAll(vecevents3);
        this.gertaerak=erantzuna;
    }
    
    public void getQuestions() {
        this.galderak = this.aukeratutakoGertaera.getQuestions();
        System.out.println(this.galderak);
    }

    public String Create(){
        return "create";
    }
    public String Show(){
        return "show";
    }

    public String Query(){
        return "query";
    }
}
