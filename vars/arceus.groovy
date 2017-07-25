import groovy.json.JsonOutput

def deploy(String host, String usernamepassword, String service, String environment, String version) {
  def payload = JsonOutput.toJson([
    deployment: [
      version: version
    ]
  ])
  sh "curl --request POST --user '${usernamepassword}' --header 'Content-Type: application/json' --data-urlencode '$payload' https://${host}/api/services/${service}/environments/${environment}/deployments"
}
