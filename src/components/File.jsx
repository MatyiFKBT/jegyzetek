import React, { useState, useEffect } from 'react';
import Highlight, { defaultProps } from 'prism-react-renderer';
import {customFields} from '@site/docusaurus.config';
import useDocusaurusContext from '@docusaurus/useDocusaurusContext';
import { useLocation } from 'react-router-dom';
/**
 * Component to display a file from a given repo and subpath. 
 * 
 * @param filename The name of the file
 * @param folder the subfolder inside the repo
 * @param repo name of the repository
 * @param lines [optional] only display these lines. (e.g L10-L20)
 */
function File({ filename, repo=customFields.repo, folder, lines = '' }) {
  const {siteMetadata} = useDocusaurusContext()
  const {pathname} = useLocation();
  const version = pathname.replace('/docs','').split('/')[1].includes('felev') ? `version-${pathname.replace('/docs','').split('/')[1]}/` : '/';
  console.log({version})
  const [content, setContent] = useState('');
  
  const language = filename.split('.')[1];
  const dev = process.env.NODE_ENV == 'development';

  const editUrl = `https://github.com/${repo}/blob/master/`;
  const docsDir = version=='/'?'docs':'versioned_docs';

  const rawDocs = `https://raw.githubusercontent.com/${repo}/master/${docsDir}/${version}`;
  function readFromX(path) {
      fetch(rawDocs + folder + '/' + filename)
        .catch((e) => {
          return { error: e };
        })
        .then((content) => {
          content.text().then((c) => {
            const contentArray = c.split('\n');
            if (lines != '') {
              const [start, end] = lines.split('-');
              const startInt = Number.parseInt(start.replace('L', ''))-1;
              const endInt = Number.parseInt(end.replace('L', ''));
              c = contentArray.slice(startInt,endInt).join("\n");
              setContent(c);
            } else {
              setContent(c);
            }
          });
        });
  }

  useEffect(() => {
    readFromX(filename);
  }, [filename]);
  //return "Read nothing";
  
  return (
    <div>
      <pre className='title'>
        <a href={editUrl + '/' +docsDir + '/' + version + folder + filename}>{filename}</a>
      </pre>
      <a href={editUrl + '/' + docsDir + '/' + version+ folder + filename + '#'+lines} className='ne'>
        <Highlight {...defaultProps} code={content} language={language}>
          {({ className, style, tokens, getLineProps, getTokenProps }) => (
            <pre className={className} style={style}>
              {tokens.map((line, i) => (
                <div {...getLineProps({ line, key: i })}>
                  {line.map((token, key) => (
                    <span {...getTokenProps({ token, key })} />
                  ))}
                </div>
              ))}
            </pre>
          )}
        </Highlight>
      </a>
    </div>
  );
}

export default File;
