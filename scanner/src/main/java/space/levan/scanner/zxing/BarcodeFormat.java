/*
 * Copyright 2007 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package space.levan.scanner.zxing;

/**
 * Enumerates barcode formats known to this package. Please keep alphabetized.
 *
 * @author Sean Owen
 */
public enum BarcodeFormat {

    /**
     * EAN-13 1D format.
     */
    EAN_13,

    /**
     * UPC-A 1D format.
     */
    UPC_A,

    /**
     * UPC-E 1D format.
     */
    UPC_E,

    /**
     * UPC/EAN extension format. Not a stand-alone format.
     */
    UPC_EAN_EXTENSION
}
