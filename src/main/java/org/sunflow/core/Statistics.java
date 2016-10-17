/*
 * Copyright (c) 2003-2007 Christopher Kulla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package org.sunflow.core;

import org.sunflow.system.UI;
import org.sunflow.system.UI.Module;

public class Statistics {
    // raytracing
    private long numEyeRays;
    private long numShadowRays;
    private long numReflectionRays;
    private long numGlossyRays;
    private long numRefractionRays;
    private long numRays;
    private long numPixels;
    // shading cache
    private long cacheHits;
    private long cacheMisses;
    private long cacheSumDepth;
    private long cacheNumCaches;

    Statistics() {
        reset();
    }

    void reset() {
        numEyeRays = 0;
        numShadowRays = 0;
        numReflectionRays = 0;
        numGlossyRays = 0;
        numRefractionRays = 0;
        numRays = 0;
        numPixels = 0;
        cacheHits = 0;
        cacheMisses = 0;
        cacheSumDepth = 0;
        cacheNumCaches = 0;
    }

    void accumulate(IntersectionState state) {
        numEyeRays += state.numEyeRays;
        numShadowRays += state.numShadowRays;
        numReflectionRays += state.numReflectionRays;
        numGlossyRays += state.numGlossyRays;
        numRefractionRays += state.numRefractionRays;
        numRays += state.numRays;
    }

    void accumulate(ShadingCache cache) {
        cacheHits += cache.hits;
        cacheMisses += cache.misses;
        cacheSumDepth += cache.sumDepth;
        cacheNumCaches += cache.numCaches;
    }

    void setResolution(int w, int h) {
        numPixels = w * h;
    }

    void displayStats() {
        // display raytracing stats
        UI.printInfo(Module.SCENE, "Raytracing stats:");
        UI.printInfo(Module.SCENE, "  * Rays traced:              (per pixel) (per eye ray) (percentage)", numRays);
        printRayTypeStats("eye", numEyeRays);
        printRayTypeStats("shadow", numShadowRays);
        printRayTypeStats("reflection", numReflectionRays);
        printRayTypeStats("glossy", numGlossyRays);
        printRayTypeStats("refraction", numRefractionRays);
        printRayTypeStats("other", numRays - numEyeRays - numShadowRays - numReflectionRays - numGlossyRays - numRefractionRays);
        printRayTypeStats("total", numRays);
        if (cacheHits + cacheMisses > 0) {
            UI.printInfo(Module.LIGHT, "Shading cache stats:");
            UI.printInfo(Module.LIGHT, "  * Lookups:             %d", cacheHits + cacheMisses);
            UI.printInfo(Module.LIGHT, "  * Hits:                %d", cacheHits);
            UI.printInfo(Module.LIGHT, "  * Hit rate:            %d%%", (100 * cacheHits) / (cacheHits + cacheMisses));
            UI.printInfo(Module.LIGHT, "  * Average cache depth: %.2f", (double) cacheSumDepth / (double) cacheNumCaches);
        }
    }

    private void printRayTypeStats(String name, long n) {
        if (n > 0)
            UI.printInfo(Module.SCENE, "      %-10s  %11d   %7.2f      %7.2f      %6.2f%%", name, n, (double) n / (double) numPixels, (double) n / (double) numEyeRays, (double) (n * 100) / (double) numRays);
    }
}