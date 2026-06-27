pipeline {
	agent any

	tools {
		maven 'Maven 3.9.16'
	}

	stages {

		stage('Clone repository') {
		    steps {
		        git branch: 'master',
		        url: 'https://github.com/pridankish/dummy-project.git'
		    }
		}

		stage('Build') {
		    steps {
		        sh 'mvn clean package'
		    }
		}

		stage('Docker clean') {
            sh 'docker ps -a -q --filter "name=spring-boot-app" | xargs -r docker stop'
		    sh 'docker ps -a -q --filter "name=spring-boot-app" | xargs -r docker rm'

		    sh 'docker images -q --filter "dangling=true | xargs -r docker rmi"'
		}

		stage('Docker build') {
		    steps {
		        sh 'docker build -t spring-boot-app:1.0 .'
		    }
		}

		stage('Run Docker') {
		    steps {
		        sh 'docker run -d --name spring-boot-app -p 9090:8080 spring-boot-app:1.0'
		    }
		}
	}
}