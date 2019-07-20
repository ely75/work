def call(Map pipelineParams) {


def Appconfig = pipelineParams

def pipelineProperties = []
def pipelineParameters = []

Appconfig.DeployEnv.each {
		pipelineParameters.add(booleanParam(description: "Déploiement en ${it.value.name} ?", name: "deploy_${it.key}", defaultValue: it.value.deploy))
	}
pipelineProperties.add(parameters(pipelineParameters))
properties(pipelineProperties)

def deploy = { environment, environmentInformations ->
	
   echo " Etape de Deploy1 ${environment}"
   echo " Etape de Deploy2 ${environmentInformations} "
}



	pipeline {
		agent none
		stages {

			stage('Build') {
				steps {
						echo  "Etape de Build from ${Appconfig['Repository']} "
					}
			}
			
			stage('Test') {
				steps {
					echo  "Etape de test ${Appconfig['appname']}"
						
					}
			}	
			
			stage('Deploy') {
				steps {
					//dir(workspace) {
							
							script {
								def deployBranches = [:]
								Appconfig.DeployEnv.each {
									if( ${params."deploy_${it.key}"} )  {
										deployBranches[it.value.name] = { deploy it.key, it.value.deploy }
									}
								}
								parallel deployBranches
							}
					//}	
						
					}
			}		

		}
	}		
}