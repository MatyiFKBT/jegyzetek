import React, { useState, useEffect } from 'react';

function Filelist({ folder, token = '' }) {
  const [contents, setContents] = useState([]);
  const repo = 'https://raw.githubusercontent.com/MatyiFKBT/jegyzetek/master/';
  async function getContents(folder) {
    folder = folder.replace('/docs/','');
    const res = await fetch(
      `https://api.github.com/repos/MatyiFKBT/jegyzetek/contents/docs/${folder}`
    );
    const text = await res.json();
    setContents(
      text.map((el) => {
        return (<a key={el.name} href={repo + el.path}>
          <li key={el.name}>{el.name}</li>
        </a>);
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
    <div>
      <h1>Fájlok</h1>
      <ul>{contents}</ul>
    </div>
  );
}

export default Filelist