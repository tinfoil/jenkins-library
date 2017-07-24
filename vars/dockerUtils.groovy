import static org.jenkinsci.plugins.docker.workflow.Docker.Container

def logContainerOutput(Container c) {
  logContainerOutput(c.id)
}

def logContainerOutput(String container_id) {
  sh("docker logs --follow ${container_id}")
}

def hasDockerImage(String image_name) {
  def status_code = sh(script: "docker inspect --format {} --type image ${image_name}", returnStatus: true)
  status_code == 0
}

def assertContainerExitCode(Container c) {
  assertContainerExitCode(c.id)
}

def assertContainerExitCode(String container_id) {
  def exitCode = sh(returnStdout: true, script: "docker inspect -f '{{.State.ExitCode}}' ${container_id}").trim().toInteger()
  if (exitCode != 0) {
    error "Docker container ${container_id} had exit code ${exitCode}"
  }
}
