def call(Map pipelineParams) {

def v_build = "a"

	pipeline {
		agent none
		stages {

			stage('Build') {
				steps {
						echo  "Etape de Build  "
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