---
slug: '/snippets'
title: Hasznos kódrészletek
sidebar_label: Kódrészletek

---
## Forrásfájl beszúrása (markdown)
```markdown
import File from '../../src/components/File';
import {customFields } from '../../docusaurus.config';
const repo = customFields.repo

## Használat

<File filename="EchoClient.py" folder="tele/gyak3" repo={repo} lines="L10-L20"/>

```

## Mermaid support
https://github.com/facebook/docusaurus/issues/1258#issuecomment-594393744
<!--stackedit_data:
eyJoaXN0b3J5IjpbMTQ4ODM0NDM2MiwtMzAxNDcwNjY1XX0=
-->