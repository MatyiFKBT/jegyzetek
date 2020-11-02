import React, { useState, useEffect } from 'react';

function Filelist({ folder, token = '' }) {
  const [contents, setContents] = useState([]);
  const repo = 'https://github.com/MatyiFKBT/jegyzetek/blob/master/';
  async function getContents(folder) {
    folder = folder.replace('/docs/', '');
    const res = await fetch(
      `https://api.github.com/repos/MatyiFKBT/jegyzetek/contents/docs/${folder}`
    );
    const text = await res.json();
    console.log(text)
    setContents(
      text.map((el) => {
        return (
          <a key={el.name} href={repo + el.path}>
            <li className="files" key={el.name}>
              {el.type=="file"?"📜":"📁"}
              {el.name}</li>
          </a>
        );
      })
    );
  }

  useEffect(() => {
    getContents(folder);
    return () => {
      '';
    };
  }, [folder]);

  return (
    <div style={{bgcolor: "#fff000"}}>
      <ul className="files">{contents}</ul>
    </div>
  );
}

export default Filelist;
