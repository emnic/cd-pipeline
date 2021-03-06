import jenkins.model.*;
import hudson.model.FreeStyleProject;
import hudson.plugins.git.GitSCM;
import hudson.tasks.Shell;
import javaposse.jobdsl.plugin.*;

def url = "https://github.com/emnic/cd-pipeline.git"
def jobName = "seed"

project = Jenkins.instance.createProject(FreeStyleProject, jobName)
def gitScm = new GitSCM(url)
gitScm.branches = [new hudson.plugins.git.BranchSpec("*/master")]
project.scm = gitScm

project.getBuildersList().clear()

project.getBuildersList().add(new ExecuteDslScripts(
  new ExecuteDslScripts.ScriptLocation("false","dsl/**/*.groovy",null),
  false,
  RemovedJobAction.IGNORE,
  RemovedViewAction.IGNORE,
  LookupStrategy.JENKINS_ROOT,
  "src/main/groovy")
);

project.save()
