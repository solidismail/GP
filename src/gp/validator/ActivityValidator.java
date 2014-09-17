package gp.validator;

import java.sql.Time;
import java.util.Date;

import gp.javabeans.Activity;
import gp.javabeans.Reunion;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ActivityValidator implements Validator{

	@Override
	public boolean supports(Class clazz) {
		
		return Activity.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name","error.not-specified-Activityname","Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "beginDate","error.not-specified-ActivitybeginDate","Required field");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endPrevisionnalDate","error.not-specified-ActivityendDate","Required field");
		

		Activity act = (Activity)obj;
		
		Date todaysDate= new Date(); 
		todaysDate.setHours(0);
		todaysDate.setMinutes(0);
		todaysDate.setSeconds(0);
		
		
		if ((act.getBeginDate()!=null)&&(act.getEndPrevisionnalDate()!=null)) {
		
			if ((act.getBeginDate()!=null)&&((act.getBeginDate().getYear()<todaysDate.getYear())||(((act.getBeginDate().getYear()==todaysDate.getYear()))&&((act.getBeginDate().getMonth()<todaysDate.getMonth())))||(((act.getBeginDate().getYear()==todaysDate.getYear()))&&((act.getBeginDate().getMonth()==todaysDate.getMonth()))&&(act.getBeginDate().getDate()<todaysDate.getDate())))
			){
			
			errors.rejectValue("beginDate", "error.date-befortoday", null, "");
			
		}
		
		if ((act.getEndPrevisionnalDate()!=null)&&((act.getEndPrevisionnalDate().getYear()<todaysDate.getYear())||(((act.getEndPrevisionnalDate().getYear()==todaysDate.getYear()))&&((act.getEndPrevisionnalDate().getMonth()<todaysDate.getMonth())))||(((act.getEndPrevisionnalDate().getYear()==todaysDate.getYear()))&&((act.getEndPrevisionnalDate().getMonth()==todaysDate.getMonth()))&&(act.getEndPrevisionnalDate().getDate()<todaysDate.getDate())))
		){
		
		errors.rejectValue("endPrevisionnalDate", "error.date-befortoday", null, "");
		
		}
		
		if ((act.getEndRealDate()!=null)&&((act.getEndRealDate().getYear()<todaysDate.getYear())||(((act.getEndRealDate().getYear()==todaysDate.getYear()))&&((act.getEndRealDate().getMonth()<todaysDate.getMonth())))||(((act.getEndRealDate().getYear()==todaysDate.getYear()))&&((act.getEndRealDate().getMonth()==todaysDate.getMonth()))&&(act.getEndRealDate().getDate()<todaysDate.getDate())))
		){
		
		errors.rejectValue("endRealDate", "error.date-befortoday", null, "");
		
		}
		
		if ((act.getBeginDate()!=null)&&(act.getEndPrevisionnalDate()!=null)&&((act.getEndPrevisionnalDate().getYear()<act.getBeginDate().getYear())||(((act.getEndPrevisionnalDate().getYear()==act.getBeginDate().getYear()))&&((act.getEndPrevisionnalDate().getMonth()<act.getBeginDate().getMonth())))||(((act.getEndPrevisionnalDate().getYear()==act.getBeginDate().getYear()))&&((act.getEndPrevisionnalDate().getMonth()==act.getBeginDate().getMonth()))&&(act.getEndPrevisionnalDate().getDate()<act.getBeginDate().getDate())))
		){
		
		errors.rejectValue("endPrevisionnalDate", "error.date-beforAndBegin", null, "");
		
		}
		if (act.getEndRealDate()!=null) {
			
		
		if ((act.getBeginDate()!=null)&&(act.getEndRealDate()!=null)&&((act.getEndRealDate().getYear()<act.getBeginDate().getYear())||(((act.getEndRealDate().getYear()==act.getBeginDate().getYear()))&&((act.getEndRealDate().getMonth()<act.getBeginDate().getMonth())))||(((act.getEndRealDate().getYear()==act.getBeginDate().getYear()))&&((act.getEndRealDate().getMonth()==act.getBeginDate().getMonth()))&&(act.getEndRealDate().getDate()<act.getBeginDate().getDate())))
		){
		
		errors.rejectValue("endRealDate", "error.date-beforAndBeginReal", null, "");
		
		}
		}
		}
	}

}
