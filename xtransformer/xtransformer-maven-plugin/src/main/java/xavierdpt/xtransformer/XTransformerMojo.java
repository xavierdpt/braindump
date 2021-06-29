package xavierdpt.xtransformer;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "xtransformer", defaultPhase = LifecyclePhase.COMPILE)
public class XTransformerMojo extends AbstractMojo {

  @Parameter(required = true, readonly = true)
  String action;

  @Parameter(defaultValue = "${project}", required = true, readonly = true)
  MavenProject project;

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("Number of dependencies:" + project.getDependencies().size());
  }
}
