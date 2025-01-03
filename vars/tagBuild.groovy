def call(String tagName, String message = 'Build tagging') {
    echo "Tagging the build with tag: ${tagName}"

    withCredentials([usernamePassword(credentialsId: 'github-credentials', usernameVariable: '$Username', passwordVariable: '$Password')]) {
    sh """  
           // git url: 'git@github.com:https://github.com/Rajani0206/Parcel-service.git
            //git config remote.origin.url 
            git tag -a ${tagName} -m "${message}"
            git push origin ${tagName}
        """
    }
}

