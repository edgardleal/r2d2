/*
 * Copyright 2013 
 *
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
package br.com.r2d2.infra;

/**
 * @author edgardleal
 * @since 19/12/2013 14:40:49
 * 
 */
public class Logger {

	public static void info(String pattern) {
		info(pattern, new Object[0]);
	}

	public static void info(String pattern, Object... args) {
		if (Config.isDebug())
			System.out.println(String.format(pattern, args));
	}
}
