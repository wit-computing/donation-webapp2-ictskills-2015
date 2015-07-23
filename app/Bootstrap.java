import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import play.*;
import play.db.jpa.Blob;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.libs.MimeTypes;
import play.test.Fixtures;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() throws FileNotFoundException
  {
    Fixtures.delete();
    Fixtures.loadModels("data.yml");
  }
}