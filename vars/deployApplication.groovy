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
		x =  ${params."Adeployer_${environment}"}
		Appconfig.DeployEnv[environment].deploy = x
	}

def deploy = { environment, environmentInformations ->
	
   echo " Etape de Deploy1 ${environment}"
   echo " Etape de Deploy2 ${environmentInformations} "
}



	pipeline {
		agent none
		stages {

			stage('Build') {
				steps {
						script {
							echo  "Etape de Build from ${Appconfig['Repository']} "
							
							Appconfig.DeployEnv.each {  miseAjourDeployParameter it.key  }
									/*echo " nom Adeployer_${it.key} "
									Appconfig.DeployEnv[it.key] = 
									echo " ${params."Adeployer_${it.key}"} "
									echo " ${params.Adeployer_rec} "*/
									
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
									if( it.value.deploy ) {
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