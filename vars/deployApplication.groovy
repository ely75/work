def call(Map pipelineParams) {


def Appconfig = pipelineParams

def pipelineProperties = []
def pipelineParameters = []

Appconfig.DeployEnv.each {
		pipelineParameters.add(booleanParam(description: "Déploiement en ${it.value.name} ?", name: "deploy_${it.key}", defaultValue: it.value.deploy))
	}
pipelineProperties.add(parameters(pipelineParameters))
properties(pipelineProperties)

	pipeline {
		agent none
		stages {

			stage('Build') {
				steps {
						echo  "Etape de Build ${Appconfig['appname']} "
					}
			}
			
			stage('Test') {
				steps {
						
						echo  "Etape de test ${Appconfig['appname']}"
						
					}
			}	
			
			stage('Deploy') {
				steps {
						echo " Etape de Deploy"
					}
			}		

		}
	}		
}