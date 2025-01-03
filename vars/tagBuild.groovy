def call(String tagName, String message = 'Build tagging') {
    echo "Tagging the build with tag: ${tagName}"

    withCredentials([usernamePassword(credentialsId: '888fe9af-1e5b-4531-b061-0686e0c595d9', usernameVariable: 'Rajani0206', passwordVariable: 'Kashvi@2211')]) {
        sh """
            
            git tag -a ${tagName} -m "${message}"
            git push origin ${tagName}
        """
    }
}
