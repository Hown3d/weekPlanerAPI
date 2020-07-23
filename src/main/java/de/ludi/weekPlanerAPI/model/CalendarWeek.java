package de.ludi.weekPlanerAPI.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class CalendarWeek {

  private int weekNumber;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @Temporal(TemporalType.DATE)
  private Date beginDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @Temporal(TemporalType.DATE)
  private Date endDate;

  public int getWeekNumber() {
    return weekNumber;
  }

  public void setWeekNumber(int weekNumber) {
    this.weekNumber = weekNumber;
  }

  public Date getBeginDate() {
    return new Date(this.beginDate.getTime());
  }

  public void setBeginDate(Date beginDate) {
    this.beginDate = new Date(beginDate.getTime());
  }

  public Date getEndDate() {
    return new Date(this.endDate.getTime());
  }

  public void setEndDate(Date endDate) {
    this.endDate = new Date(endDate.getTime());
  }
}
