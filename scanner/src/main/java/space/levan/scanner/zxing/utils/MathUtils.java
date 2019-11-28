/*
 * Copyright 2012 ZXing authors
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

package space.levan.scanner.zxing.utils;

/**
 * General math-related and numeric utility functions.
 */
public final class MathUtils {

    private MathUtils() {
    }

    /**
     * @param aX point A x coordinate
     * @param aY point A y coordinate
     * @param bX point B x coordinate
     * @param bY point B y coordinate
     * @return Euclidean distance between points A and B
     */
    public static float distance(float aX, float aY, float bX, float bY) {
        double xDiff = aX - bX;
        double yDiff = aY - bY;
        return (float) Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

}
