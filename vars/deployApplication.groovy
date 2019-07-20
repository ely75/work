def call(Map pipelineParams) {


def Appconfig = pipelineParams

def pipelineProperties = []
def pipelineParameters = []

Appconfig.DeployEnv.each {
		pipelineParameters.add(booleanParam(description: "Déploiement en ${it.value.name} ?", name: "Adeployer_${it.key}", defaultValue: it.value.deploy))
	}
pipelineProperties.add(parameters(pipelineParameters))
properties(pipelineProperties)

def miseAjourDeployParameter = { environment ->
		Appconfig.DeployEnv[environment].deploy = params["Adeployer_${environment}"]
	}

def deploy = { environment, environmentInformations ->
	
   echo " Etape de Deploy1 ${environment}"
   echo " Etape de Deploy2 ${environmentInformations} "
}



	pipeline {
		agent any
		stages {

			stage('Build') {
				steps {
						script {
							echo  "Etape de Build from ${Appconfig['Repository']} "
							libTools.miseAjourDeployParameter(Appconfig, params)
							libTools.checkout(Appconfig)
						}
							
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
									if( Appconfig.DeployEnv[it.key].deploy ) {
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