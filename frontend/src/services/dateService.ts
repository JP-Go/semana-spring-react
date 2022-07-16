function convertToISODate (date:Date) {
  return date.toISOString().slice(0, 10);
}
const dateService = {
  convertToISODate
};
export default dateService;
