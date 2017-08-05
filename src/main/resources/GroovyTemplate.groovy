import java.io.StringWriter;
import java.util.Date;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

VelocityEngine ve = new VelocityEngine();
ve.addProperty("resource.loader", "file");
ve.addProperty("file.resource.loader.class",
        "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
ve.addProperty("file.resource.loader.cache", false);
ve.addProperty("file.resource.loader.path", System.getProperty("java.io.tmpdir"));
ve.init();
Template t = ve.getTemplate("___PATH_TO_VELOCITY_TEMPLATE___", "UTF-8");
VelocityContext context = new VelocityContext();
//==============================================================================
//PUT THIS PLACE YOUR VARIBLES FROM VELOCITY TEMPLATE
//==============================================================================
context.put("date", new Date());







//==============================================================================
StringWriter writer = new StringWriter();
t.merge( context, writer );
writer.toString();  