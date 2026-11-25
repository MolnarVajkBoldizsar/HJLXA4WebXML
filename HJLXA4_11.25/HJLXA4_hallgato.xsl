<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="hu">
            <head>
                <meta charset="UTF-8"/>
                <title>Hallgatok adatai - for-each, value-of</title>
                <style>
                    body { font-family: Arial, sans-serif; }
                    h2 { text-align: center; }
                    table { margin: 0 auto; border: 3px solid #333; border-collapse: collapse; }
                    th { background: #83c94d; }
                    th, td { border: 1px solid #333; padding: 4px; }
                </style>
            </head>
            <body>
                <h2>Hallgatok adatai - for-each, value-of</h2>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Vezeteknev</th>
                        <th>Keresztnev</th>
                        <th>Becenev</th>
                        <th>Kor</th>
                        <th>Fizetes</th>
                    </tr>
                    <xsl:for-each select="class/student">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="vezeteknev"/></td>
                            <td><xsl:value-of select="keresztnev"/></td>
                            <td><xsl:value-of select="becenev"/></td>
                            <td><xsl:value-of select="kor"/></td>
                            <td><xsl:value-of select="fizetes"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
