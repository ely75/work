def call(Map pipelineParams) {

def v_build = pipelineParams['build']

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
						
						echo  "Etape de test "
						
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