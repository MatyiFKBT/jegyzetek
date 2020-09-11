import React, { useState, useEffect } from 'react';
import Highlight, { defaultProps } from 'prism-react-renderer';

import { editUrl } from '../../package.json';

function File(props) {
  const [content, setContent] = useState('');
  const language = props.filename.split('.')[1];

  function readFromX(path) {
    //console.log(path);
    fetch(path)
      .catch((e) => {
        return { error: e };
      })
      .then((content) => {
        content.text().then((c) => {
          console.log(c.split('\n'));
          setContent(c);
        });
      });
  }

  useEffect(() => {
    readFromX(props.filename);
  }, [props.filename]);
  //return "Read nothing";

  return (
    <div>
      <pre className="title">
        <a href={editUrl + props.filename}>{props.filename}</a>
      </pre>
      <a href={editUrl + props.filename} className='ne'>
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
