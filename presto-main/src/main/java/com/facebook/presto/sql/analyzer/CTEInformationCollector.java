/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.presto.sql.analyzer;

import com.facebook.presto.spi.eventlistener.CTEInformation;
import com.facebook.presto.sql.tree.QualifiedName;
import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.List;

public class CTEInformationCollector
{
    private final HashMap<String, CTEInformation> cteInformationMap = new HashMap<>();

    public void addCTEReference(QualifiedName cteName, boolean isView)
    {
        cteInformationMap.putIfAbsent(cteName.toString(), new CTEInformation(cteName.toString(), 0, isView));
        cteInformationMap.get(cteName.toString()).incrementReferences();
    }

    public List<CTEInformation> getCTEInformationList()
    {
        return ImmutableList.copyOf(cteInformationMap.values());
    }
}
