def call(Map pipelineParams) {

def v_build = pipelineParams['build']
def config = pipelineParams

	pipeline {
		agent none
		stages {

			stage('Build') {
				steps {
						echo  "Etape de Build ${v_build} "
					}
			}
			
			stage('Test') {
				steps {
						
						echo  "Etape de test ${config['test']}"
						
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