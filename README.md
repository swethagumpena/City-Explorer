# City explorer

(As part of CS 478 - Software Development for Mobile Platforms)

Suite of two Android apps designed to help users plan their next vacation to either Orlando, Florida or New York City. The first app, CityChooser, allows users to choose between the two cities and broadcasts an intent to the second app, CityDetails, which responds by launching one of two activities showing information about tourist attractions in the selected city. Both activities in CityDetails contain two fragments: one displaying a scrollable list of tourist attractions and the other showing the official website of the selected attraction using a WebView widget. In portrait mode, the two fragments are displayed on separate screens, while in landscape mode, they are shown side-by-side. The state of the app is retained across device reconfigurations.
