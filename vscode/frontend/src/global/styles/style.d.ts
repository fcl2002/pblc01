import 'styled-components';

import dark from './theme/dark';
import light from './theme/light';

export type ITheme = typeof dark | typeof light;

declare module 'styled-components' {
	export interface DefaultTheme extends ITheme {}
}
