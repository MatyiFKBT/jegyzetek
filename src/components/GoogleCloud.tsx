import React from 'react';

function GoogleCloud({ folder, file }) {
  const repo = 'https://github.com/MatyiFKBT/jegyzetek';

  return (
    <a
      href={`https://ssh.cloud.google.com/cloudshell/editor?cloudshell_git_repo=${repo}&cloudshell_open_in_editor=${file}&cloudshell_working_dir=${folder}`}
    >
      <img
        alt='Open in Cloud Shell'
        // src='https://img.shields.io/badge/google%20cloud-open-blue?style=for-the-badge&logo=google-cloud'
        src='https://gstatic.com/cloudssh/images/open-btn.svg'
      />
    </a>
  );
}

export default GoogleCloud;
